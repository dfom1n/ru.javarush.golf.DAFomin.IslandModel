package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Animal implements Predator{
    public Eagle() {
//        super(6, 20, 3, 1,
//                ThreadLocalRandom.current().nextDouble(0.4,1));
        super(Settings.get().getCreaturesCommonSpecsByType("Eagle"));
    }
}
