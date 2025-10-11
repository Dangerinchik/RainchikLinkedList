package com.minispring;

public class ForestHunt implements Hunt {

    Hunter hunter = new ArmoredHunter();
    Hunter hunter2 = new AncientHunter();
    Jaeger jaeger = new JaegerImpl();
    public void produceHunt() {
        startHunt();
        jaeger.manageHunt();
        hunter.hunt();
        hunter2.hunt();
        stopHunt();
    }

    @Override
    public void startHunt() {
        System.out.println("We go to the forest");
    }

    @Override
    public void stopHunt() {
        System.out.println("We go out of the forest");
    }

}
