package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.predators.Predator;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Animal implements Herbivore, Predator {
    public Duck() {
//        super(1, 200, 4, 0.15,
//                ThreadLocalRandom.current().nextDouble(0,0.15));
        super(Settings.get().getCreaturesCommonSpecsByType("Duck"));
    }
}
