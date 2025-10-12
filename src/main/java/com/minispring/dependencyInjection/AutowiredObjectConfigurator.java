package com.minispring.dependencyInjection;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AutowiredObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object t, MiniApplicationContext context) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for(Field field : t.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Autowired.class)){
                field.setAccessible(true);
                Object object = context.getBean(field.getType());
                field.set(t, object);
            }
        }
    }
}
