package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.predators.Predator;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Animal implements Herbivore, Predator {
    public Mouse() {
//        super(0.05, 500, 1, 0.01,
//                ThreadLocalRandom.current().nextDouble(0.00001, 0.01));
        super(Settings.get().getCreaturesCommonSpecsByType("Mouse"));
    }
}
