package com.skynet;

import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Simulation {

    private final RobotFactory factory = new RobotFactory();
    private final Faction world = new Faction("World");
    private final Faction wednesday = new Faction("Wednesday");
    private final int days = 100;

    public void start() {

        Phaser phaser = new Phaser(3);

        Thread factoryThread = new Thread(() -> {
            for (int day = 1; day <= days; day++) {
                try {

                    int produced = factory.fulfillDayQuote();

                    System.out.println("Day " + day + ": Factory produced " + produced + " parts");

                    phaser.arriveAndAwaitAdvance();

                    phaser.arriveAndAwaitAdvance();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread worldThread = new Thread(() -> runFaction(world, phaser));
        Thread wednesdayThread = new Thread(() -> runFaction(wednesday, phaser));

        factoryThread.start();
        worldThread.start();
        wednesdayThread.start();

        try {
            factoryThread.join();
            worldThread.join();
            wednesdayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(determineWinner(world, wednesday));
    }

    private void runFaction(Faction faction, Phaser phaser) {
        for (int day = 1; day <= days; day++) {
            try {
                phaser.arriveAndAwaitAdvance();

                List<Part> taken = faction.getPart(factory);
                System.out.println(faction.getName() + " took parts on day " + day + ": " + taken);

                faction.createRobot();
                System.out.println(faction.getName() + " robots after day " + day + ": " + faction.getRobots().size());

                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private String determineWinner(Faction faction1, Faction faction2){
        if(faction1.getRobots().size() == faction2.getRobots().size()){
            return "It's a draw";
        }
        else if (faction1.getRobots().size() >= faction2.getRobots().size()) {
            return faction1.getName() + " wins!";
        }
        else {
            return faction2.getName() + " wins!";
        }

    }


}
