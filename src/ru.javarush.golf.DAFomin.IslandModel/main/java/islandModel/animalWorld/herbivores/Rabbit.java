package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Rabbit extends Animal implements Herbivore {
    public Rabbit() {
//        super(2, 150, 2, 0.45,
//              ThreadLocalRandom.current().nextDouble(0.001,0.45));
        super(Settings.get().getCreaturesCommonSpecsByType("Rabbit"));
    }
}
