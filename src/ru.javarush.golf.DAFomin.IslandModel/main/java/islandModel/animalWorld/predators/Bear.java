package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Bear extends Animal implements Predator{
    public Bear() {
        super(Settings.get().getCreaturesCommonSpecsByType("Bear"));
    }
}
