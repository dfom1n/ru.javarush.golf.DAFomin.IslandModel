package islandModel.services;

import islandModel.island.Island;
import islandModel.island.Location;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SimulationLocationLife implements Runnable{
    private final Island island;
    private final Location location;
    private final Queue<TaskLocation> tasks = new ConcurrentLinkedQueue<>();

    public SimulationLocationLife(Island island, Location location) {
        this.island = island;
        this.location = location;
    }

    @Override
    public void run() {

                createTasksLocation();

    }


    private void createTasksLocation() {
        if (location != null) {
            location.getLock().lock();
            try {

                    tasks.add(new TaskLocation(island, location));

            } finally {
                location.getLock().unlock();
            }
        }

        tasks.forEach(TaskLocation::perform);
        tasks.clear();
    }


}
