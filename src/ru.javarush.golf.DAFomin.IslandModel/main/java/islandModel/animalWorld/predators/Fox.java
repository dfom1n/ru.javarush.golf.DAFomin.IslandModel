package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Animal implements Predator {
    public Fox() {
//        super(8, 30, 2, 2,
//                ThreadLocalRandom.current().nextDouble(0.5,2));
        super(Settings.get().getCreaturesCommonSpecsByType("Fox"));
    }
}
