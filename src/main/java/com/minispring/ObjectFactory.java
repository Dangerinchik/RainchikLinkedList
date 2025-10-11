package com.minispring;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    public static ObjectFactory getInstance() { return ourInstance; }

    public ObjectFactory() {

    }

    public <T> T createObject(Class<T> type) {
        
    }
}
