package com.njcit.concurrent1;

/**
 * @Author LiJun
 * @Date 2020/5/7 13:15
 * 在调用wait()方法时，线程必须要持有被调用对象的锁，当调用wait()方法后
 * 线程就会释放掉该对象的锁(monitor)。在调用Thread类的sleep()方法后，线程是不会释放对象的锁的
 */

public class MyTest1 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        synchronized (object){
            object.wait();
        }
    }
}
