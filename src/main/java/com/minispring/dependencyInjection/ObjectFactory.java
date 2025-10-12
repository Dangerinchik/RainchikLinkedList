package com.minispring.dependencyInjection;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {

    private final MiniApplicationContext context;

    private List<ObjectConfigurator> configurators = new ArrayList<>();

    public ObjectFactory(MiniApplicationContext context) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }


    public <T> T createObject(Class<T> implClass) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        T t = create(implClass);

        configure(t);

        initialize(t);

        invokeInit(implClass, t);
        return t;

    }

    private static <T> void initialize(T t) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if(InitializingBean.class.isAssignableFrom(t.getClass())) {
            t.getClass().getMethod("afterPropertiesSet").invoke(t);
        }
    }

    private static <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for(Method m : implClass.getMethods()) {
            if(m.isAnnotationPresent(PostConstruct.class)) {
                m.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(configurator -> {
            try {
                configurator.configure(t, context);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
