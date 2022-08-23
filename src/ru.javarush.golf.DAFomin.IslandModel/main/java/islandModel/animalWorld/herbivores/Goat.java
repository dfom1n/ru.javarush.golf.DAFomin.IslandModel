package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Goat extends Animal implements Herbivore {
    public Goat() {
        super(Settings.get().getCreaturesCommonSpecsByType("Goat"));
    }
}
