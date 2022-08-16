package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Animal implements Predator{
    public Bear() {
//        super(500, 5, 2, 80,
//               ThreadLocalRandom.current().nextDouble(5,80));
        super(Settings.get().getCreaturesCommonSpecsByType("Bear"));
    }
}
