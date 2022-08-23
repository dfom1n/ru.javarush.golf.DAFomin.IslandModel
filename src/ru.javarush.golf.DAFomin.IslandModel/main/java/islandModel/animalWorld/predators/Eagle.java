package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Eagle extends Animal implements Predator{
    public Eagle() {
        super(Settings.get().getCreaturesCommonSpecsByType("Eagle"));
    }
}
