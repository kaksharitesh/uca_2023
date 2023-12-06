package com.uca.test;

public class Singleton {

    public static int age = 0;
    private static Singleton instance = new Singleton(); //eager loading
    private static Integer mutex = 0;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //lazy loading
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new Singleton();  //synchronized -- thread safe
                }
            }
        }
        return instance;
    }

    public int hashCode() {
        return 10;
    }

    public int increaseAge() {
        int t = age;
        t = t + 1;
        age = t;
        return age;
    }
}
