package com.njcit.concurrent3;

import java.util.Date;

/**
 * @Author LiJun
 * @Date 2020/5/8 13:21
 */

public class MyTest1 {
    private Object object = new Object();

    public void method(){
        synchronized (object){
            System.out.println("hello world");
            throw new RuntimeException();
        }
    }

    public void method2(){
        synchronized (object){
        }
    }
}
