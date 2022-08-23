package islandModel.services;

import islandModel.island.Island;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationIslandLife extends Thread{
    private final Island island;

//ПРОДОЛЖИТЕЛЬНОСТЬ ЖИЗНЕННОГО ЦИКЛА Settings.get().getCycleDuration()
    private final long LIFE_CYCLE_DURATION = Settings.get().getCycleDuration();
//ОСТАНОВКА ПО ТАЙМ-АУТУ Settings.get().getStopOnTimeout()
    private final Boolean STOP_ON_TIMEOUT = Settings.get().getStopOnTimeout();
    //    ПРОДОЛЖИТЕЛЬНОСТЬ ИГРЫ Settings.get().getGameDuration()
    private final int GAME_DURATION = Settings.get().getGameDuration();
    public SimulationIslandLife(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        island.print();
        //
        ScheduledExecutorService simulationScheduledThreadPool = Executors.newScheduledThreadPool(4);
        simulationScheduledThreadPool.scheduleWithFixedDelay(this::runAndWaitLocationLife, LIFE_CYCLE_DURATION, LIFE_CYCLE_DURATION, TimeUnit.MILLISECONDS);

        if (STOP_ON_TIMEOUT) runTimer();
    }

//    ArrayList<OrganismWorker> organismWorkers = new ArrayList<>();
//        for (String organismType : Settings.get().getOrganismsTypes()) {
//        organismWorkers.add(new OrganismWorker(organismType, game.getWorld()));
//    }
//    //
//    int CORE_POOL_SIZE = 4;
//    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
//        for (OrganismWorker organismWorker : organismWorkers) {
//        fixedThreadPool.submit(organismWorker);
//    }
//        fixedThreadPool.shutdown();
//
//        try {
//        if (fixedThreadPool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS)) {
//            game.showStatistics();
//        }
//    } catch (InterruptedException e) {
//        //
//        System.out.println("The game is finished");
//    }

//    метод вызвать у локации
    private void runAndWaitLocationLife() {
        ArrayList<SimulationLocationLife> simulationLocationLives = new ArrayList<>();
        for (String creaturesType : Settings.get().getCreaturesTypes()) {
            simulationLocationLives.add(new SimulationLocationLife(island, creaturesType));
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
