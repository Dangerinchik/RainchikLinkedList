package com.minispring;

public class JaegerImpl implements Jaeger {
    @InjectByType
    private Alcohol alcohol;

    @Override
    public void manageHunt() {
        System.out.println("Hello, I'm jaeger. AND I'M THE LAW HERE!");
        alcohol.drink();
    }
}
