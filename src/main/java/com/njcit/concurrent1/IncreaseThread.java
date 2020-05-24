package com.njcit.concurrent1;

/**
 * @Author LiJun
 * @Date 2020/5/7 17:32
 */

public class IncreaseThread extends Thread {

    private MyObject myObject;

    public IncreaseThread(MyObject myObject){
        this.myObject = myObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep((long)Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject.increase();
        }
    }
}
