package com.weub.myboot.model.singleton;

public class Singleton {

    private String name;

    private Singleton() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Singleton getUniqueInstance() {
        return SingletonHolder.Instance;
    }

    private static class SingletonHolder {
        private static final Singleton Instance = new Singleton();
    }

    /*public static void main(String[] args) {
        Singleton singleton = Singleton.getUniqueInstance();
        singleton.setName("A");

        Singleton singleton2 = Singleton.getUniqueInstance();
        System.out.println(singleton2.getName());
    }*/
}

class SingletonB {
    private static volatile SingletonB uniqueInstance;

    private SingletonB() {}

    public static SingletonB getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (SingletonB.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SingletonB();
                }
            }
        }
        return uniqueInstance;
    }
}
