package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Animal implements Herbivore {
    public Goat() {
//        super(60, 140, 3, 10,
//                ThreadLocalRandom.current().nextDouble(2,10));
        super(Settings.get().getCreaturesCommonSpecsByType("Goat"));
    }
}
