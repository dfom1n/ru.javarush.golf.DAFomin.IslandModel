package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Animal implements Predator {
    public Boa() {
//        super(15, 30, 1, 3,
//                ThreadLocalRandom.current().nextDouble(1,3));
        super(Settings.get().getCreaturesCommonSpecsByType("Boa"));
    }
}
