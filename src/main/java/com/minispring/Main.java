package com.minispring;

import com.minispring.components.AncientHunter;
import com.minispring.components.ArmoredHunter;
import com.minispring.components.Hunt;
import com.minispring.dependencyInjection.Application;
import com.minispring.dependencyInjection.MiniApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MiniApplicationContext context = Application.run("com.minispring", new HashMap<>(Map.of(ArmoredHunter.class, AncientHunter.class)));
        Hunt hunt = context.getBean(Hunt.class);
        hunt.produceHunt();
    }
}
