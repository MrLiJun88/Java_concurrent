package com.njcit.concurrent2;

/**
 * @Author LiJun
 * @Date 2020/5/8 12:44
 * 当一个线程去访问某个static synchronized 方法时，获取的这个锁是当前的对象对应的Class对象的锁
 *
 * 当某一个对象里面有若干个synchronized方法，那么这些普通的
 * synchronized方法在某一时刻被多个线程所访问时，只能有其中一个方法
 * 被线程去调用，因为当一个线程进入到synchronized方法时，就会先获得
 * 这个对象的锁。
 */

public class MyThreadTest2 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
//        MyClass myClass2 = new MyClass();

        Thread1 t1 = new Thread1(myClass);
        Thread2 t2 = new Thread2(myClass);

        t1.start();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
class MyClass {

    public synchronized void hello() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}

class Thread1 extends Thread {
    private MyClass myClass;

    public Thread1(MyClass myClass){
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class Thread2 extends Thread {
    private MyClass myClass;

    public Thread2(MyClass myClass){
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.world();
    }
}
