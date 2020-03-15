package com.back.reference;

public class Reference {
    private String name;

    public Reference(String name) {
        this.name = name;
    }

    public Reference() {
        System.out.println("This is Reference's constructor.");
    }

    public static void staticMethod() {
        System.out.println("This is Reference's static method.");
    }

    public void commonMethod() {
        System.out.println("This is Reference's common method.");
    }

    public void getName() {
        System.out.println("This is Reference's instance:" + this.name);
    }

}
