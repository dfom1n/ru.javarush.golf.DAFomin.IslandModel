package islandModel.animalWorld;

import islandModel.animalWorld.herbivores.Caterpillar;
import islandModel.island.*;

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




//Лучше стоит мув перекинуть в клас лакатион


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
