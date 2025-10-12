package com.minispring.dependencyInjection;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Application {
    public static MiniApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Config config = new JavaConfig(packageToScan, ifc2ImplClass);
        MiniApplicationContext context = new MiniApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);

        return context;

    }
}
