package islandModel.island;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.herbivores.*;
import islandModel.animalWorld.plant.Plant;
import islandModel.animalWorld.predators.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


public class Location {

    private Coordinates coordinates;
    Location[][] locations;
    private final Lock lock = new ReentrantLock(true);
    //    В локацию рандомно закидываем животных
    private LocationListAnimal<? extends Animal> locationListAnimal;
    //    В локацию рандомно закидываем растения
    private LocationListPlant<? extends Plant> locationListPlant;

    public Location(Coordinates coordinates) {
        this.coordinates = coordinates;
        locationListAnimal = new LocationListAnimal<>();
        locationListPlant = new LocationListPlant<>();
        locationListPlant.creatingPlantRandom();
        locationListAnimal.creatingAnimalsRandom();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocationListAnimal getLocationListAnimal() {
        return locationListAnimal;
    }

    public LocationListPlant getLocationListPlant() {
        return locationListPlant;
    }



    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        HashMap<String, Integer> locationStatistics = new HashMap<>();
        for (Animal animal : this.locationListAnimal.getListAnimal()) {
            locationStatistics.put(animal.getAnimalCharacteristics().getIcon(), (int) this.locationListAnimal.getListAnimal().stream().filter(e -> e.getClass().getName().equals(animal.getClass().getName())).count());
        }
        for (Plant plant : this.locationListPlant.getListPlant()) {
            locationStatistics.put(plant.plantName(), (int) this.locationListPlant.getListPlant().stream().filter(e -> e.getClass().getName().equals(plant.getClass().getName())).count());
        }
        String locationStatisticsString = this.getCoordinates().showCoordinate() + locationStatistics.toString();
        return locationStatisticsString;
    }
}


