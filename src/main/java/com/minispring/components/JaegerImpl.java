package com.minispring.components;

import com.minispring.dependencyInjection.Autowired;
import com.minispring.dependencyInjection.Component;
import com.minispring.dependencyInjection.InitializingBean;

import javax.annotation.PostConstruct;

@Component
public class JaegerImpl implements Jaeger, InitializingBean {
    @Autowired
    private Alcohol alcohol;
    @Autowired
    private Alcohol alcohol2;

    @PostConstruct
    public void init(){
        System.out.println(alcohol == alcohol2);
    }

    @Override
    public void manageHunt() {
        System.out.println("Hello, I'm jaeger. AND I'M THE LAW HERE!");
        alcohol.drink();
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Properties are setted");
        System.out.println(alcohol.getClass());
        System.out.println(alcohol2.getClass());
        alcohol.drink();
    }
}
