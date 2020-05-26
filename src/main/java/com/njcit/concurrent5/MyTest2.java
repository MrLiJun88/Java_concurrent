package com.njcit.concurrent5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: LiJun
 * @Date: 2020/5/24 9:05 下午
 *
 * CountDownLatch使用案例:有一个线程有若干个子任务，主任务需要等待它所有的子任务完成后，才开始执行
 */

public class MyTest2 {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        IntStream.range(0,3).forEach(i -> new Thread(() -> {
            try{
                Thread.sleep(2000);
                System.out.println("hello");
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                //让countDownLatch中count-1，必须写在finally语句块中，确保被执行
                countDownLatch.countDown();
            }
        }).start());

        System.out.println("启动子线程完毕");
        try{
            //检查countDownLatch中count是否为0，若为0，则await()立即返回，否则进入到阻塞队列中，直到count为0
            //countDownLatch.await(200, TimeUnit.MILLISECONDS);
            countDownLatch.await();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("主线程执行完毕");
    }
}
