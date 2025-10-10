package com.skynet;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class RobotFactory {
    private List<Part> storedParts = new ArrayList<Part>();
    private Random random = new Random();

    public void fulfillDayQuote(){
        while (storedParts.size() < 10){
            createPart();
        }
    }

    public void createPart() {
            int partNumber = random.nextInt(1, 5);
            switch (partNumber) {
                case 1 -> storedParts.add(Part.HEAD);
                case 2 -> storedParts.add(Part.TORSO);
                case 3 -> storedParts.add(Part.HAND);
                case 4 -> storedParts.add(Part.FEET);
            }
        }

}