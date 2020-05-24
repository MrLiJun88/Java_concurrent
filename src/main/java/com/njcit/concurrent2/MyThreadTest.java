package com.njcit.concurrent2;

/**
 * @Author LiJun
 * @Date 2020/5/8 12:27
 */

public class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);

        t1.start();
        t2.start();
    }
}
class MyThread implements Runnable {

    int x;

    @Override
    public void run() {
        x = 0;
        while (true){
            System.out.println("result: " + x++);
            try {
                Thread.sleep((long)Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(30 == x){
                break;
            }
        }
    }
}
