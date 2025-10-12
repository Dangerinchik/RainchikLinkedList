package com.minispring.dependencyInjection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    BeanScope value() default BeanScope.SINGLETON;

}

