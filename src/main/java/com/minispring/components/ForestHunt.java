package com.minispring.components;

import com.minispring.dependencyInjection.Autowired;
import com.minispring.dependencyInjection.Component;

@Component
public class ForestHunt implements Hunt {
    @Autowired
    Hunter hunter;
    @Autowired
    Hunter hunter2;
    @Autowired
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
