package com.uca.test;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread());
        Thread t3 = new Thread(new MyThread());
        Thread t4 = new Thread(new MyThread());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        Thread.sleep(30);
        System.out.println(Thread.currentThread().getName());
        Singleton s = Singleton.getInstance();
        System.out.println(Thread.currentThread().getName() + " found " + s.increaseAge());
    }

    private static class MyThread implements Runnable {

        @Override
        public void run() {
            Singleton s = Singleton.getInstance();
            System.out.println("current age is " + s.increaseAge());
            System.out.println(Thread.currentThread().getName() + " created " + s.hashCode());
        }
    }
}
