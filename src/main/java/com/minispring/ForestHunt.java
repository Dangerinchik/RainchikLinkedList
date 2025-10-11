package com.minispring;

public class ForestHunt implements Hunt {

    Hunter hunter = ObjectFactory.getInstance().createObject(Hunter.class);
    Hunter hunter2 = ObjectFactory.getInstance().createObject(Hunter.class);
    Jaeger jaeger = ObjectFactory.getInstance().createObject(JaegerImpl.class);
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
