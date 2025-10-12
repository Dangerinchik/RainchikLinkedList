package com.minispring.components;

import com.minispring.dependencyInjection.BeanScope;
import com.minispring.dependencyInjection.Component;
import com.minispring.dependencyInjection.InjectProperty;
import com.minispring.dependencyInjection.Scope;

@Component
@Scope(BeanScope.PROTOTYPE)
public class AlcoholImpl implements Alcohol {
    @InjectProperty("wisky")
    private String alco;

    @Override
    public void drink(){
        System.out.println("Drink " + alco);
    }

}
