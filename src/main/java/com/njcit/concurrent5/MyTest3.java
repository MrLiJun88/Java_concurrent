package com.njcit.concurrent5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: LiJun
 * @Date: 2020/5/26 10:41 上午
 *
 * CyclicBarrier：设置一个屏障，等待所有的线程都达到后，一起执行，然后取消屏障
 * 相比于CountDownLatch来说，一旦计数器为0，则不会再改变
 * 而CyclicBarrier是可重用的，将所有等待线程都到达后，CyclicBarrier又恢复到最初的状态
 * ，就可被重用
 */

public class MyTest3 {
    public static void main(String[] args) {
        //定义CyclicBarrier，并指定参与的线程数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try{
                    Thread.sleep((long)(Math.random() * 5000));

                    int randomInt = new Random().nextInt(500);
                    System.out.println("hello " + randomInt);

                    //检查线程数量是否已经达到三个，如果没有则陷入等待,
                    // 一旦当第三个线程到达，则等待的线程将一起同时执行
                    cyclicBarrier.await();

                    System.out.println("world: " + randomInt);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
