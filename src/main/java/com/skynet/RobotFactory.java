package com.skynet;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
@Setter
public class RobotFactory {
    private final BlockingQueue<Part> storedParts = new LinkedBlockingQueue<>(10);
    private Random random = new Random();

    public int fulfillDayQuote() throws InterruptedException {
        int amount = 0;
        for(int i = 0; i<10; i++){
            createPart();
            amount++;
            }
        return amount;
    }

    public void createPart() throws InterruptedException {
            int partNumber = random.nextInt(1, 5);
            Part part = switch (partNumber) {
                case 1 -> Part.HEAD;
                case 2 -> Part.TORSO;
                case 3 -> Part.HAND;
                case 4 -> Part.FEET;
                default -> null;
            };
            storedParts.put(part);
        }

}