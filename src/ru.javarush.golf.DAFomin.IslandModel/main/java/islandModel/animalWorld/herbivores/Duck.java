package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.predators.Predator;
import islandModel.services.Settings;

public class Duck extends Animal implements Herbivore, Predator {
    public Duck() {
        super(Settings.get().getCreaturesCommonSpecsByType("Duck"));
    }
}
