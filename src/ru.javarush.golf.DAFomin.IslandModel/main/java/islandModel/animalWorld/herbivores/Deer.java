package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Deer extends Animal implements Herbivore {
    public Deer() {
        super(Settings.get().getCreaturesCommonSpecsByType("Deer"));
    }
}
