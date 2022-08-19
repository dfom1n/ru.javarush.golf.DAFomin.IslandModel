package islandModel.island;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.herbivores.Caterpillar;
import islandModel.animalWorld.herbivores.Herbivore;
import islandModel.animalWorld.plant.Plant;
import islandModel.animalWorld.predators.ChancesPredatorEat;
import islandModel.animalWorld.predators.Predator;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class LocationWorker {
    public boolean eatingHerbivoreSafe(Location location) {
        location.getLock();
        try {
            return location.eatingHerbivore();
        } finally {
            location.getLock().unlock();
        }
    }
    public void starveAnimalSafe(Location location) {
        location.getLock();
        try {
            location.starveAnimal();
        } finally {
            location.getLock().unlock();
        }
    }

    public boolean eatingPredatorSafe(Location location) {
        location.getLock();
        try {
            return location.eatingPredator();
        } finally {
            location.getLock().unlock();
        }
    }

    public void breedingSafe(Location location) {
        location.getLock();
        try {
            location.breeding();
        } finally {
            location.getLock().unlock();
        }
    }


    public void movingSafe(Island island, Location location) {
        location.getLock();
        try {
            location.moving(island);
        } finally {
            location.getLock().unlock();
        }
    }
}
