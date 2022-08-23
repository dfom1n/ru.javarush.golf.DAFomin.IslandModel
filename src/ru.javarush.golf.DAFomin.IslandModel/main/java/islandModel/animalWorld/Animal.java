package islandModel.animalWorld;

import islandModel.animalWorld.herbivores.Caterpillar;
import islandModel.animalWorld.herbivores.Herbivore;
import islandModel.animalWorld.plant.Plant;
import islandModel.animalWorld.predators.ChancesPredatorEat;
import islandModel.animalWorld.predators.Predator;
import islandModel.island.*;
import islandModel.services.Settings;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {
    private SpecificationCreaturesCharacteristics animalCharacteristics;

    public Animal(SpecificationCreaturesCharacteristics animalCharacteristics) {
        this.animalCharacteristics = animalCharacteristics;
    }

    public SpecificationCreaturesCharacteristics getAnimalCharacteristics() {
        return animalCharacteristics;
    }

    public boolean eatingHerbivore(Location location) {

        return eatingHerbivoreSafe(location);

    }

    public <T extends Animal & Herbivore> boolean eatingHerbivoreSafe(Location location) {
        location.getLock().lock();
        try {
            boolean eatingHerbivoreBoolean = false;
            if ((this instanceof Herbivore && this.getAnimalCharacteristics().getAmountFoodToSatiate() - this.getAnimalCharacteristics().getSatiety() != 0)) {
                location.getLocationListPlant().getListPlant().removeIf(plant -> {
                    Plant plantEat = (Plant) location.getLocationListPlant().getListPlant().get(ThreadLocalRandom.current().nextInt(location.getLocationListPlant().getListPlant().size() - 1));
                    ((Herbivore) this).eatPlant(this, plantEat);
//                    System.out.println("Травоядные кушают: Herbivore" + this.getAnimalCharacteristics().getIcon() + " " + "plant " + " " + plantEat.getAnimalCharacteristics().getIcon());
                    return plant.equals(plantEat);
                });
                eatingHerbivoreBoolean = true;
            } else starve(location, this);
            return eatingHerbivoreBoolean;
        } finally {
            location.getLock().unlock();
        }
    }


    public void starveAnimal(Location location) {
        starveAnimalSafe(location);
    }


    public <T extends Animal> void starveAnimalSafe(Location location) {
        location.getLock().lock();
        try {
            starve(location, this);

        } finally {
            location.getLock().unlock();
        }
    }

    public <T extends Animal> void starve(Location location, T animal) {
        double starvation = animal.getAnimalCharacteristics().getAmountFoodToSatiate() * Settings.get().getUnviableWeight();
        animal.getAnimalCharacteristics().setSatiety(animal.getAnimalCharacteristics().getSatiety() - starvation);
        if (animal.getAnimalCharacteristics().getSatiety() <= 0) {
            location.getLocationListAnimal().getListAnimal().removeIf(e -> e.equals(animal));
//            System.out.println("Умерло животное " + this.getAnimalCharacteristics().getIcon());
        }
    }


    public boolean eatingPredator(Location location) {
        return eatingPredatorSafe(location);
    }

    public <T extends Animal> boolean eatingPredatorSafe(Location location) {
        location.getLock().lock();
        try {
            boolean eatingPredatorBoolean = false;
         T victim = (T) location.getLocationListAnimal().getListAnimal().get(ThreadLocalRandom.current().nextInt(location.getLocationListAnimal().getListAnimal().size() - 1));
            if (this instanceof Predator && chancesEatingPredator(this, victim)) {
                location.getLocationListAnimal().getListAnimal().removeIf(animal -> {
                    ((Predator) this).eatAnimal(this, victim);
                    return animal.equals(victim);
                });
                eatingPredatorBoolean = true;
            } else starve(location, this);
//            System.out.println("Охота: Predator" + " " + this.getAnimalCharacteristics().getIcon() + " " + "getAmountFoodToSatiate" + " " + this.getAnimalCharacteristics().getAmountFoodToSatiate() + " " + "getSatiety" + " " + this.getAnimalCharacteristics().getSatiety() + " " + (this.getAnimalCharacteristics().getAmountFoodToSatiate() - this.getAnimalCharacteristics().getSatiety()) + " " + "Victim" + " " + victim.getAnimalCharacteristics().getIcon() + " " + victim.getAnimalCharacteristics().getWeight());
            return eatingPredatorBoolean;
        } finally {
            location.getLock().unlock();
        }
    }

    public <T extends Animal> boolean chancesEatingPredator(T predator, T victim) {
        boolean chanceEating = false;
        String predatorName = predator.getClass().getSimpleName();
       String victimName = victim.getClass().getSimpleName();
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

    public void breeding(Location location) {
        breedingSafe(location);
    }

    public <T extends Animal> void breedingSafe(Location location) {
        location.getLock().lock();
        try {
            double maxCountSpecies = this.getAnimalCharacteristics().getMaxCountSpecies();
            double countingAnimal = location.getLocationListAnimal().countingAnimal(this);
            if (countingAnimal < maxCountSpecies && countingAnimal > 1) {
                location.getLocationListAnimal().settingAnimals(this);
//                System.out.println("Животное родилось " + this.getAnimalCharacteristics().getIcon());
            }
        } finally {
            location.getLock().unlock();
        }
    }

    public void moving(Island island, Location location) {
        movingSafe(island, location);
    }

    public <T extends Animal> void movingSafe(Island island, Location location) {
        location.getLock().lock();
        try {
             if (this.move(location, island)) {
                location.getLocationListAnimal().getListAnimal().removeIf(animal -> animal.equals(this));
//                System.out.println("Животное переместилось " + this.getAnimalCharacteristics().getIcon());
            }
        } finally {
            location.getLock().unlock();
        }
    }

    public <T extends Animal> boolean move(Location location, Island island) {
        Location locationMove = null;
        directionMovement directionMovement = directionMovementRandom();
        int moveSpeedRandom = movementSpeedRandom(this);
        int oldLocationX = location.getCoordinates().getOrdinateX();
        int oldLocationY = location.getCoordinates().getOrdinateY();
        int locationMoveX = oldLocationX + (directionMovement.getX() * moveSpeedRandom);
        int locationMoveY = oldLocationY + (directionMovement.getY() * moveSpeedRandom);
        if ((oldLocationX == locationMoveX && oldLocationY == locationMoveY) || (locationMoveX < 0 || locationMoveY < 0)) {
            locationMove = null;
        } else if (island.getSizeIsland().getLength() - 1 >= locationMoveX && island.getSizeIsland().getWidth() - 1 >= locationMoveY) {
            locationMove = island.moveToLocation(locationMoveX, locationMoveY);
            locationMove.getLocationListAnimal().setAnimal(this);
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


    public String animalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalCharacteristics, animal.animalCharacteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalCharacteristics);
    }
}
