package com.njcit.concurrent3;

/**
 * @Author LiJun
 * @Date 2020/5/11 15:52
 */

public class MyTest6 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void myMethod1(){
        synchronized (lock1){
            synchronized (lock2){
                System.out.println("myMethod1 invoked");
            }
        }
    }

    public void myMethod2(){
        synchronized (lock2){
            synchronized (lock1){
                System.out.println("myMethod2 invoked");
            }
        }
    }

    public static void main(String[] args) {
        MyTest6 myTest6 = new MyTest6();

        Runnable runnable = () -> {
            while (true){
                myTest6.myMethod1();
                try{
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(runnable,"myThread1");

        Runnable runnable2 = () -> {
            while (true){
                myTest6.myMethod2();
                try{
                    Thread.sleep(250);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread(runnable2,"myThread2");

        thread1.start();
        thread2.start();
    }
}

