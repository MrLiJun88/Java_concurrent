package com.njcit.concurrent4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author LiJun
 * @Date 2020/5/12 20:52
 */

public class MyTest2 {
    /**可重入锁*/
    private Lock lock = new ReentrantLock();

    private void myMethod1(){
        try{
            lock.lock();
            System.out.println("myMethod1 invoked");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finally {
            lock.unlock();
        }
    }

    private void myMethod2(){
        try{
            lock.lock();
            System.out.println("myMethod2 invoked");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        finally {
            lock.unlock();
        }
//        boolean result = false;
//        try{
//            result = lock.tryLock(800,TimeUnit.MILLISECONDS);
//        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        if(result){
//            System.out.println("get the lock");
//        }
//        else {
//            System.out.println("can't get the lock");
//        }
    }

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest2.myMethod1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest2.myMethod2();

            }
        });

        t1.start();
        t2.start();
    }
}
