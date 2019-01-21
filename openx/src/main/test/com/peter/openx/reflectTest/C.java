package com.peter.openx.reflectTest;

public class C implements B {
    public static void main(String[] args){
        C c = new C();
        for (Class<?> aClass : c.getClass().getInterfaces()) {
            System.out.println(aClass.getName());
        }
    }
}
