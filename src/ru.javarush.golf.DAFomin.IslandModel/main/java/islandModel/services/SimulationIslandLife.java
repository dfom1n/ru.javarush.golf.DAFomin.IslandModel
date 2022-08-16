package islandModel.services;

import islandModel.island.Island;
import islandModel.island.Location;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationIslandLife extends Thread{
    private final Island island;

//ПРОДОЛЖИТЕЛЬНОСТЬ ЖИЗНЕННОГО ЦИКЛА Settings.get().getCycleDuration()
    private final long LIFE_CYCLE_DURATION = 50;
//ОСТАНОВКА ПО ТАЙМ-АУТУ Settings.get().getStopOnTimeout()
    private final Boolean STOP_ON_TIMEOUT = true;
    //    ПРОДОЛЖИТЕЛЬНОСТЬ ИГРЫ Settings.get().getGameDuration()
    private final int GAME_DURATION = 30000;
    public SimulationIslandLife(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        island.print();
        //
        ScheduledExecutorService simulationScheduledThreadPool = Executors.newScheduledThreadPool(8);
        simulationScheduledThreadPool.scheduleWithFixedDelay(this::runAndWaitLocationLife, LIFE_CYCLE_DURATION, LIFE_CYCLE_DURATION, TimeUnit.MILLISECONDS);

        if (STOP_ON_TIMEOUT) runTimer();
    }

//    метод вызвать у локации
    private void runAndWaitLocationLife() {
        ArrayList<SimulationLocationLife> simulationLocationLives = new ArrayList<>();
        for (Location location : island.getLocationList()) {
            simulationLocationLives.add(new SimulationLocationLife(island, location));
        }
        //
        int CORE_POOL_SIZE = 4;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        for (SimulationLocationLife simulationLocationLife: simulationLocationLives) {
            fixedThreadPool.submit(simulationLocationLife);
        }
        fixedThreadPool.shutdown();

        try {
            if (fixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS)) {
                island.print();
            }
        } catch (InterruptedException e) {
            //
            System.out.println("The game is finished");
        }
    }

    private void runTimer() {
        try {
            Thread.sleep(GAME_DURATION);
        } catch (InterruptedException e) {
            System.out.println("The game is already finished");
        }
        //
        System.out.println("The game is over by timeout");
        System.exit(1);
    }

}