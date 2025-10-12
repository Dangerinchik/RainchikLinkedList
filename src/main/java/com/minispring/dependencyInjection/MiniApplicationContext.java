package com.minispring.dependencyInjection;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MiniApplicationContext {
    @Setter
    private ObjectFactory objectFactory;
    private Map<Class,Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public MiniApplicationContext(Config config) {
        this.config = config;
    }


    public <T> T getBean(Class<T> type) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        if(cache.containsKey(type)){
            return (T)cache.get(type);
        }

        Class<? extends T> implClass = type;

        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }

        T t = objectFactory.createObject(implClass);

        cashingIfNeeded(type, implClass, t);

        return t;
    }

    private <T> void cashingIfNeeded(Class<T> type, Class<? extends T> implClass, T t) {
        Scope scopeAnnotation = implClass.getAnnotation(Scope.class);

        if(scopeAnnotation == null || scopeAnnotation.value() == BeanScope.SINGLETON) {
            if (implClass.isAnnotationPresent(Component.class)) {
                cache.put(type, t);
            }
        }
    }

}
