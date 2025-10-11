package com.minispring;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.minispring", new HashMap<>(Map.of(ArmoredHunter.class, AncientHunter.class)));
        Hunt hunt = context.getObject(Hunt.class);
        hunt.produceHunt();
    }
}
