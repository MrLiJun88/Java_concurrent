package com.njcit.concurrent4;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Author LiJun
 * @Date 2020/5/19 13:40
 */

public class MyTest4 {
    public static void main(String[] args) {
        BoundedContainer container = new BoundedContainer();
        //放置线程
        IntStream.range(0,10).forEach(i -> new Thread(() -> {
            try{
                container.put("hello" + i);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start());
        //取出线程
        IntStream.range(0,8).forEach(i -> new Thread(() -> {
            try{
                container.take();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start());
    }
}

/**定义一个有界容器*/
class BoundedContainer {
    private String[] elements = new String[10];
    private Lock lock = new ReentrantLock();
    private Condition notEmptyCondition = lock.newCondition();
    private Condition notFullCondition = lock.newCondition();
    /**elements数组中已有的元素数量*/
    private int elementCount;
    /**放置元素的索引*/
    private int putIndex;
    /**取元素的索引*/
    private int takeIndex;

    /**
     * 放置元素
     * @param element 待放置的元素
     */
    public void put(String element) throws InterruptedException{
        lock.lock();
        try{
            //数组已满
            while(elementCount == elements.length){
                notFullCondition.await();
            }
            //放置元素到数组中
            elements[putIndex] = element;
            //下一个元素已经到最后的元素
            if(++putIndex == elements.length){
                putIndex = 0;
            }
            elementCount++;
            System.out.println("put method: " + Arrays.toString(elements));
            //发出取元素信号
            notEmptyCondition.signal();
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * 取出元素
     * @return
     */
    public String take() throws InterruptedException {
        lock.lock();
        try{
            //数组中没有元素
            while(0 == elementCount){
                notEmptyCondition.await();
            }
            //取出元素
            String result = elements[takeIndex];
            //将数组中取出元素的位置设为null
            elements[takeIndex] = null;
            //当取元素到数组末尾时
            if(++takeIndex == elements.length){
                takeIndex = 0;
            }
            elementCount--;
            System.out.println("take method: " + Arrays.toString(elements));
            //通知放置元素的线程
            notFullCondition.signal();
            return result;
        }
        finally {
            lock.unlock();
        }
    }
}
