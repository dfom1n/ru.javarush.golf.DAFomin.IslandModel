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

    public <T extends Animal & Herbivore> boolean eatingHerbivore() {
        boolean eatingHerbivoreBoolean = false;
        List<T> listHerbivore = (List<T>) locationListAnimal.getListAnimal().stream().filter(animal -> animal instanceof Herbivore).collect(Collectors.toList());
        T herbivore = listHerbivore.get(ThreadLocalRandom.current().nextInt(listHerbivore.size() - 1));
        if (herbivore.getAnimalCharacteristics().getAmountFoodToSatiate() - herbivore.getAnimalCharacteristics().getSatiety() != 0) {
            locationListPlant.getListPlant().removeIf(plant -> {
                Plant plantEat = locationListPlant.getListPlant().get(ThreadLocalRandom.current().nextInt(locationListPlant.getListPlant().size() - 1));
                herbivore.eatPlant(herbivore, plantEat);
                return plant.equals(plantEat);
            });
            eatingHerbivoreBoolean = true;
        } else starve(herbivore);
//        System.out.println("Herbivore" + " " + herbivore.getClass().getName() + " " + "getAmountFoodToSatiate" + " " + herbivore.getAmountFoodToSatiate() + " " + "getSatiety" + " " + herbivore.getSatiety() + " " + (herbivore.getAmountFoodToSatiate() - herbivore.getSatiety()));
//        System.out.println(listHerbivore);
        return eatingHerbivoreBoolean;
    }


    public <T extends Animal> void starveAnimal() {
        T animal = (T) locationListAnimal.getListAnimal().get(ThreadLocalRandom.current().nextInt(locationListAnimal.getListAnimal().size() - 1));
        starve(animal);
    }

    public <T extends Animal> void starve(T animal) {
        double starvation = animal.getAnimalCharacteristics().getAmountFoodToSatiate()*0.9;
        animal.getAnimalCharacteristics().setSatiety(animal.getAnimalCharacteristics().getSatiety()-starvation);
        if (animal.getAnimalCharacteristics().getSatiety()<=0){
            locationListAnimal.getListAnimal().removeIf(e -> e.equals(animal));
        }
    }


    public <T extends Animal & Predator> boolean eatingPredator() {
        boolean eatingPredatorBoolean = false;
        List<T> listPredators = (List<T>) locationListAnimal.getListAnimal().stream().filter(animal -> animal instanceof Predator).collect(Collectors.toList());
//        T predator = (T) locationListAnimal.getListAnimal().stream().filter(animal -> animal instanceof Predator).findAny().stream().collect(Collectors.toList());
        T predator = listPredators.get(ThreadLocalRandom.current().nextInt(listPredators.size() - 1));
        T victim = (T) locationListAnimal.getListAnimal().get(ThreadLocalRandom.current().nextInt(locationListAnimal.getListAnimal().size() - 1));
//        predator.eatAnimal(predator, victim);
//        System.out.println(locationListAnimal.getListAnimal().stream().filter(animal -> animal.equals(victim)).count());
        if (chancesEatingPredator(predator, victim)) {
            locationListAnimal.getListAnimal().removeIf(animal -> {
                predator.eatAnimal(predator, victim);
                return animal.equals(victim);
            });
            eatingPredatorBoolean = true;
        } else starve(predator);
//        System.out.println("Predator" + " " + predator.getClass().getName() + " " + "getAmountFoodToSatiate" + " " + predator.getAmountFoodToSatiate() + " " + "getSatiety" + " " + predator.getSatiety() + " " + (predator.getAmountFoodToSatiate() - predator.getSatiety()) + " " + "Victim" + " " + victim.getClass().getName() + " " + victim.getWeight());
        return eatingPredatorBoolean;
    }

    public <T extends Animal> boolean chancesEatingPredator(T predator, T victim) {
        boolean chanceEating = false;
        String predatorName = predator.getClass().getSimpleName();
//                getName().toLowerCase().substring(predator.getClass().getName().lastIndexOf(".") + 1, predator.getClass().getName().length());
//        System.out.println(predatorName);
        String victimName = victim.getClass().getSimpleName();
//                getName().toLowerCase().substring(victim.getClass().getName().lastIndexOf(".") + 1, victim.getClass().getName().length());
//        System.out.println(victimName);
        ChancesPredatorEat chancesPredatorEat = new ChancesPredatorEat();
        int chance = chancesPredatorEat.chanceToGetEatPredator(predatorName, victimName);
        int chanceEat = 0;
        if (chance > 0) {
            chanceEat = ThreadLocalRandom.current().nextInt(chance, 101);
            if (chanceEat == 100) {
                chanceEating = true;
            }
        }
//        System.out.println("chanceEating: " + chanceEating + " predatorName: " + predatorName + " victimName: " + victimName + " chance: " + chance + " chanceEat: " + chanceEat);
        return chanceEating;
    }

    public <T extends Animal> void breeding() {
        T animalBreeding = (T) locationListAnimal.getListAnimal().get(ThreadLocalRandom.current().nextInt(this.locationListAnimal.getListAnimal().size()));
        double maxCountSpecies = animalBreeding.getAnimalCharacteristics().getMaxCountSpecies();
        double countingAnimal = this.locationListAnimal.countingAnimal(animalBreeding);
        if (countingAnimal < maxCountSpecies && countingAnimal > 1) {
            this.locationListAnimal.settingAnimals(animalBreeding);
        }
    }


    public <T extends Animal> void moving(Island island) {
        T animalMove = (T) locationListAnimal.getListAnimal().get(ThreadLocalRandom.current().nextInt(this.locationListAnimal.getListAnimal().size()));
        if (animalMove.move(getCoordinates(), island)) {
            locationListAnimal.getListAnimal().removeIf(animal -> animal.equals(animalMove));
//            System.out.println(animalMove.getClass().getName());
        }

    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        HashMap<String, Integer> locationStatistics = new HashMap<>();
        for (Animal animal : this.locationListAnimal.getListAnimal()) {
            locationStatistics.put(animal.animalName(), (int) this.locationListAnimal.getListAnimal().stream().filter(e->e.getClass().getName().equals(animal.getClass().getName())).count());
        }
        for (Plant plant : this.locationListPlant.getListPlant()) {
            locationStatistics.put(plant.plantName(), (int) this.locationListPlant.getListPlant().stream().filter(e->e.getClass().getName().equals(plant.getClass().getName())).count());
        }
        String locationStatisticsString = this.getCoordinates().showCoordinate() + locationStatistics.toString();
        return locationStatisticsString;
    }
}


