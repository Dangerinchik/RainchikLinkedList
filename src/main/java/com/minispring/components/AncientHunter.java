package com.minispring.components;

import com.minispring.dependencyInjection.Component;

@Component
public class AncientHunter implements Hunter {
    @Override
    public void hunt() {
        System.out.println("I'm hunting with spear");
    }
}
