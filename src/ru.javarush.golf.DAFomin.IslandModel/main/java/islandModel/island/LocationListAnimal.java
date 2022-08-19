package islandModel.island;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.herbivores.*;
import islandModel.animalWorld.predators.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LocationListAnimal<T extends Animal> {
    private double maxCountAnimalsLocation = 2345d;
    private final List<T> listAnimalDefault = new ArrayList<>();
    private double countAnimalsLocation;
    public List<T> listAnimal = new ArrayList<>();

    public LocationListAnimal() {
        listAnimalDefault.add((T) new Buffalo());
        listAnimalDefault.add((T) new Caterpillar());
        listAnimalDefault.add((T) new Deer());
        listAnimalDefault.add((T) new Duck());
        listAnimalDefault.add((T) new Goat());
        listAnimalDefault.add((T) new Horse());
        listAnimalDefault.add((T) new Mouse());
        listAnimalDefault.add((T) new Rabbit());
        listAnimalDefault.add((T) new Sheep());
        listAnimalDefault.add((T) new WildBoar());
        listAnimalDefault.add((T) new Bear());
        listAnimalDefault.add((T) new Boa());
        listAnimalDefault.add((T) new Eagle());
        listAnimalDefault.add((T) new Fox());
//        listAnimalDefault.add((T) new Wolf());
        this.countAnimalsLocation = ThreadLocalRandom.current().nextDouble(maxCountAnimalsLocation);
    }


    public void creatingAnimalsRandom() {
        while (listAnimal.size() < countAnimalsLocation) {
            T animal = listAnimalDefault.get(ThreadLocalRandom.current().nextInt(listAnimalDefault.size()));
            if (animal.getAnimalCharacteristics().getMaxCountSpecies() > listAnimal.stream().filter(a -> a.getClass().getName().equals(animal.getClass().getName())).count()) {
                settingAnimals(animal);
            }
        }
    }
    public <T> void settingAnimals(T animal){
        if (animal instanceof Buffalo) {
            setAnimal(new Buffalo());
        } else if (animal instanceof Caterpillar) {
            setAnimal((new Caterpillar()));
        } else if (animal instanceof Deer) {
            setAnimal((new Deer()));
        } else if (animal instanceof Duck) {
            setAnimal((new Duck()));
        } else if (animal instanceof Goat) {
            setAnimal((new Goat()));
        } else if (animal instanceof Horse) {
            setAnimal((new Horse()));
        } else if (animal instanceof Mouse) {
            setAnimal((new Mouse()));
        } else if (animal instanceof Rabbit) {
            setAnimal((new Rabbit()));
        } else if (animal instanceof Sheep) {
            setAnimal((new Sheep()));
        } else if (animal instanceof WildBoar) {
            setAnimal((new WildBoar()));
        } else if (animal instanceof Bear) {
            setAnimal((new Bear()));
        } else if (animal instanceof Boa) {
            setAnimal((new Boa()));
        } else if (animal instanceof Eagle) {
            setAnimal((new Eagle()));
        } else if (animal instanceof Fox) {
            setAnimal((new Fox()));
        } else if (animal instanceof Wolf) {
            setAnimal((new Wolf()));
        }

    }


    public void setAnimal(Animal animal) {
        listAnimal.add((T) animal);
    }

    public List<T> getListAnimal() {
//        System.out.println(listAnimal);
        return listAnimal;
    }

    public double getCountAnimalsLocation() {
        return countAnimalsLocation;
    }

    public <T extends Animal> double countingAnimal(T animal) {
        double countingAnimal = getListAnimal().stream().filter(a -> a.getClass().getName().equals(animal.getClass().getName())).count();
        return countingAnimal;
    }

}
