package com.minispring;

@Singleton
public class AlcoholImpl implements Alcohol {
    @InjectProperty("wisky")
    private String alco;

    @Override
    public void drink(){
        System.out.println("Drink " + alco);
    }

}
