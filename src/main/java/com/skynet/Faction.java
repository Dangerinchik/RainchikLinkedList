package com.skynet;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class Faction {
    private String name;
    private List<Robot> robots = new ArrayList<>();
    private List<Part> carriedParts = new ArrayList<>();
    private final List<Part> partsForRobot = List.of(Part.HEAD, Part.TORSO, Part.HAND, Part.HAND, Part.FEET, Part.FEET);
    private int nextRobotId = 1;

    public Faction(String name) {
        this.name = name;
    }

    public List<Part> getPart(RobotFactory factory) throws InterruptedException {
        List<Part> parts = new ArrayList<>();
        for(int i = 0; i < 5 && !factory.getStoredParts().isEmpty(); i++) {
            Part part = factory.getStoredParts().take();
            if (part != null) {
                carriedParts.add(part);
                parts.add(part);
            }

        }

        return parts;
    }

    public void createRobot(){
        boolean canBuild = true;
        List<Part> tempParts = new ArrayList<>(carriedParts);
        for(Part part : partsForRobot){
            if(!tempParts.remove(part)){
                canBuild = false;
                break;
            }
        }
        if(canBuild){
            Robot robot = new Robot(nextRobotId++, new ArrayList<>(partsForRobot));
            robots.add(robot);

            for(Part part : partsForRobot){
                carriedParts.remove(part);
            }
        }
    }

}