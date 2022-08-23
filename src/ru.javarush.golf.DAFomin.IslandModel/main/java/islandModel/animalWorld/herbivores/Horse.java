package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Horse extends Animal implements Herbivore {
    public Horse() {
        super(Settings.get().getCreaturesCommonSpecsByType("Horse"));
    }
}
