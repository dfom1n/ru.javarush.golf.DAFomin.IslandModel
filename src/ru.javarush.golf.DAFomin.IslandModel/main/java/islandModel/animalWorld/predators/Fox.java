package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Fox extends Animal implements Predator {
    public Fox() {
        super(Settings.get().getCreaturesCommonSpecsByType("Fox"));
    }
}
