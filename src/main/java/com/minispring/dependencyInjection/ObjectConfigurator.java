package com.minispring.dependencyInjection;

import java.lang.reflect.InvocationTargetException;

public interface ObjectConfigurator {
    public void configure(Object t, MiniApplicationContext context) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException;
}
