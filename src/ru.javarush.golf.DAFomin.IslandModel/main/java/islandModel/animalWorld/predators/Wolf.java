package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Wolf extends Animal implements Predator {
    public Wolf() {
        super(Settings.get().getCreaturesCommonSpecsByType("Wolf"));
    }
}
