package com.minispring;

public class JaegerImpl implements Jaeger {

    private Alcohol alcohol = ObjectFactory.getInstance().createObject(Alcohol.class);

    @Override
    public void manageHunt() {
        System.out.println("Hello, I'm jaeger. AND I'M THE LAW HERE!");
        alcohol.drink();
    }
}
