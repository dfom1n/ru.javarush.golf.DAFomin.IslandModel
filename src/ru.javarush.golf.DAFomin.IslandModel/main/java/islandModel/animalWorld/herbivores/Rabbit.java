package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Rabbit extends Animal implements Herbivore {
    public Rabbit() {
        super(Settings.get().getCreaturesCommonSpecsByType("Rabbit"));
    }
}
