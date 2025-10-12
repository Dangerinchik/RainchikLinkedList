package com.minispring.components;

import com.minispring.dependencyInjection.Component;

@Component
public class ArmoredHunter implements Hunter {
    @Override
    public void hunt() {
        System.out.println("I'm hunting with winchester");
    }
}
