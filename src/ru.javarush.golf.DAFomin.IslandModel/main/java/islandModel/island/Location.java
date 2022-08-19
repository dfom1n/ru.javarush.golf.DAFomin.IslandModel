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
//                System.out.println(herbivore.getAnimalCharacteristics().getIcon() + " " + plant + " " + plantEat);
                return plant.equals(plantEat);
            });
            eatingHerbivoreBoolean = true;
        } else starve(herbivore);
//        System.out.println("Herbivore" + " " + herbivore.getAnimalCharacteristics().getName() + " " + "getAmountFoodToSatiate" + " " + herbivore.getAnimalCharacteristics().getAmountFoodToSatiate() + " " + "getSatiety" + " " + herbivore.getAnimalCharacteristics().getSatiety() + " " + (herbivore.getAnimalCharacteristics().getAmountFoodToSatiate() - herbivore.getAnimalCharacteristics().getSatiety()));
//        System.out.println(listHerbivore);
        return eatingHerbivoreBoolean;
    }


    public <T extends Animal> void starveAnimal() {
        T animal = (T) locationListAnimal.getListAnimal().get(ThreadLocalRandom.current().nextInt(locationListAnimal.getListAnimal().size() - 1));
        starve(animal);
    }

    public <T extends Animal> void starve(T animal) {
        double starvation = animal.getAnimalCharacteristics().getAmountFoodToSatiate() * 0.9;
        animal.getAnimalCharacteristics().setSatiety(animal.getAnimalCharacteristics().getSatiety() - starvation);
        if (animal.getAnimalCharacteristics().getSatiety() <= 0) {
            locationListAnimal.getListAnimal().removeIf(e -> e.equals(animal));
//            System.out.println("умерло животное");
        }
    }


    public <T extends Animal & Predator> boolean eatingPredator() {
        boolean eatingPredatorBoolean = false;
        List<T> listPredators = (List<T>) locationListAnimal.getListAnimal().stream().filter(animal -> animal instanceof Predator).collect(Collectors.toList());
//        T predator = (T) locationListAnimal.getListAnimal().stream().filter(animal -> animal instanceof Predator).findAny().stream().collect(Collectors.toList());
        T predator = listPredators.get(ThreadLocalRandom.current().nextInt(listPredators.size() - 1));
        T victim = (T) locationListAnimal.getListAnimal().get(ThreadLocalRandom.current().nextInt(locationListAnimal.getListAnimal().size() - 1));
//        System.out.println(predator.getAnimalCharacteristics().getIcon() + " " + victim.getAnimalCharacteristics().getIcon());
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
        if (move(animalMove, getCoordinates(), island)) {
            locationListAnimal.getListAnimal().removeIf(animal -> animal.equals(animalMove));
//            System.out.println(animalMove);
        }
    }

    public <T extends Animal> boolean move(T animal, Coordinates coordinates, Island island) {
        Location locationMove = null;
        directionMovement directionMovement = directionMovementRandom();
        int moveSpeedRandom = movementSpeedRandom(animal);
//        берем координыты действующей локации
        int oldLocationX = coordinates.getOrdinateX();
        int oldLocationY = coordinates.getOrdinateY();
//        ищем координыты новой локации (подставляем к к координатам действующей локации енам)
        int locationMoveX = oldLocationX + (directionMovement.getX() * moveSpeedRandom);
        int locationMoveY = oldLocationY + (directionMovement.getY() * moveSpeedRandom);
//        если локация с новыми координатами существует
        if ((oldLocationX == locationMoveX && oldLocationY == locationMoveY) || (locationMoveX < 0 || locationMoveY < 0)) {
            locationMove = null;
        } else if (island.getSizeIsland().getLength() - 1 >= locationMoveX && island.getSizeIsland().getWidth() - 1 >= locationMoveY) {
            locationMove = island.moveToLocation(locationMoveX, locationMoveY);
//            System.out.println(animal);
            locationMove.getLocationListAnimal().setAnimal(animal);
        }
        if (locationMove != null) {
            return true;
        } else return false;
    }


    public directionMovement directionMovementRandom() {
        return directionMovement.values()[ThreadLocalRandom.current().nextInt(directionMovement.values().length - 1)];
    }

    public <T extends Animal> Integer movementSpeedRandom(T animal) {
        if (animal instanceof Caterpillar) {
            return 0;
        } else
            return ThreadLocalRandom.current().nextInt(1, animal.getAnimalCharacteristics().getMaxSpeedToLocation() + 1);
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


