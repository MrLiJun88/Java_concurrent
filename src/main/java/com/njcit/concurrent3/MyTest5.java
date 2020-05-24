package com.njcit.concurrent3;

/**
 * @Author LiJun
 * @Date 2020/5/10 16:04
 */

public class MyTest5 {

    Object object = new Object();

    public void method(){
        synchronized (object){
            System.out.println("hello world");
        }
        synchronized (object){
            System.out.println("welcome");
        }
        synchronized (object){
            System.out.println("person");
        }
    }
}
