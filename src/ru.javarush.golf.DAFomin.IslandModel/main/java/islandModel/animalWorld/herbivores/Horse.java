package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Animal implements Herbivore {
    public Horse() {
//        super( 400, 20, 4, 60,
//                ThreadLocalRandom.current().nextDouble(5,60));
        super(Settings.get().getCreaturesCommonSpecsByType("Horse"));
    }
}
