package com.njcit.concurrent1;

/**
 * @Author LiJun
 * @Date 2020/5/7 17:37
 */

public class Client {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        Thread increaseThread = new IncreaseThread(myObject);
        Thread increaseThread2 = new IncreaseThread(myObject);

        Thread decreaseThread = new DecreaseThread(myObject);
        Thread decreaseThread2 = new DecreaseThread(myObject);

        increaseThread.start();
        increaseThread2.start();
        decreaseThread.start();
        decreaseThread2.start();
    }
}
