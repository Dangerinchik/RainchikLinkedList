package com.skynet;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Faction {
    private String name;
    private List<Robot> robots = new ArrayList<>();
    private List<Part> carriedParts = new ArrayList<>();
    private final List<Part> partsForRobot = List.of(Part.HEAD, Part.TORSO, Part.HAND, Part.HAND, Part.FEET, Part.FEET);
    private int nextRobotId = 1;

    public void getPart(RobotFactory factory){
        if(carriedParts.size() < 5){
            carriedParts.add(factory.getStoredParts().remove(0));
        }
    }

    public void createRobot(){
        if(carriedParts.containsAll(partsForRobot)){
            Robot robot = new Robot(nextRobotId, carriedParts);
            robots.add(robot);
        }
    }

}