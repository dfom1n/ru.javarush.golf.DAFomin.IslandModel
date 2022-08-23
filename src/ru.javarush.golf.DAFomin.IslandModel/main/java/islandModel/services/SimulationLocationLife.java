package islandModel.services;

import islandModel.animalWorld.Animal;
import islandModel.island.Island;
import islandModel.island.Location;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class SimulationLocationLife implements Runnable{
    private final Island island;
    private final String creaturesType;
    private final Queue<TaskLocation> tasks = new ConcurrentLinkedQueue<>();

    public SimulationLocationLife(Island island, String creaturesType) {
        this.island = island;
        this.creaturesType = creaturesType;
    }

    @Override
    public void run() {
        Location[][] locations = island.getLocations();
        for (Location[] lengthLocations : locations) {
            for (Location location : lengthLocations) {
                createTasksLocation(location);
            }
        }
    }


    private void createTasksLocation(Location location) {
        List<? extends Animal> animals = (List<? extends Animal>) location.getLocationListAnimal().getListAnimal().stream().filter(animal -> animal.getClass().getSimpleName().equals(creaturesType)).collect(Collectors.toList());
        if (animals != null) {
            location.getLock().lock();
            try {
                for (Animal animal : animals) {
                    tasks.add(new TaskLocation(island, location, animal));
                }

            } finally {
                location.getLock().unlock();
            }
        }
        tasks.forEach(TaskLocation::perform);
        tasks.clear();
    }


}
