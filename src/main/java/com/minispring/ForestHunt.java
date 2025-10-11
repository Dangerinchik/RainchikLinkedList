package com.minispring;

public class ForestHunt implements Hunt {
    @InjectByType
    Hunter hunter;
    @InjectByType
    Hunter hunter2;
    @InjectByType
    Jaeger jaeger;
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
