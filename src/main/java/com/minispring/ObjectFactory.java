package com.minispring;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    public static ObjectFactory getInstance() { return ourInstance; }

    @SneakyThrows
    public ObjectFactory() {

        config = new JavaConfig("com.minispring", new HashMap<>(Map.of(Hunter.class, AncientHunter.class)));
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }
        T t = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(configurator -> configurator.configure(t));

        return t;

    }
}
