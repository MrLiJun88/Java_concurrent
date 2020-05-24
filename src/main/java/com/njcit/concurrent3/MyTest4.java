package com.njcit.concurrent3;

/**
 * @Author LiJun
 * @Date 2020/5/10 15:28
 */

public class MyTest4 {

//    private Object object = new Object();

    public void method(){
        Object object = new Object();
        synchronized (object){
            System.out.println("hello world");
        }
    }
}
