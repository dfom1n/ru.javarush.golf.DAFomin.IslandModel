package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Animal implements Herbivore {
    public Deer() {
//        super(300, 20, 4, 50,
//                ThreadLocalRandom.current().nextDouble(5,50));
        super(Settings.get().getCreaturesCommonSpecsByType("Deer"));
    }
}
