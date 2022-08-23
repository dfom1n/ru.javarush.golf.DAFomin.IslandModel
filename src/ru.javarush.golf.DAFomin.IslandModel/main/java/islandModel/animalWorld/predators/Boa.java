package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Boa extends Animal implements Predator {
    public Boa() {
        super(Settings.get().getCreaturesCommonSpecsByType("Boa"));
    }
}
