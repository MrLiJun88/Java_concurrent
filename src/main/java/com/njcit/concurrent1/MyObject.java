package com.njcit.concurrent1;

/**
 * @Author LiJun
 * @Date 2020/5/7 17:09
 *
 * 编写一个多线程程序，实现这样一个目标
 * 1.存在一个对象，该对象有一个int类型的成员变量counter，该成员变量初始值为0
 * 2.创建两个线程，其中一个线程对该对象的成员变量+1，另一个线程对该对象的成员变量-1
 * 3.输出该对象成员变量counter每次变化后的值
 * 4.最终输出的结果应为：10101010...
 */

public class MyObject {

   private int counter;

    public synchronized void increase() {
        //说明已经是1了
        while (1 == counter) {
            try{
                /**说明此处counter已经为1
                 * 需要释放掉锁，让其他线程去减1
                 */
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //为0，则加1
        counter++;
        System.out.println(counter);
        notify();
    }

   public synchronized void decrease(){
        while(0 == counter){
            try{
                /**说明此处counter已经为0
                 * 需要释放掉锁，让其他线程去加1
                 */
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //为1，则减1
        counter--;
       System.out.println(counter);
       notify();
   }
}
