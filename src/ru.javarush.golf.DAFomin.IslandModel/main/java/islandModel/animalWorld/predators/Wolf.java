package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal implements Predator {
    public Wolf() {
//        super(50, 30, 3, 8,
//                ThreadLocalRandom.current().nextDouble(2,8));
        super(Settings.get().getCreaturesCommonSpecsByType("Wolf"));
    }
}
