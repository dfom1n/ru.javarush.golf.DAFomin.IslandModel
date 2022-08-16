package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.predators.Predator;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class WildBoar extends Animal implements Herbivore, Predator {
    public WildBoar() {
//        super(400, 50, 2, 50,
//               ThreadLocalRandom.current().nextDouble(1,50));
        super(Settings.get().getCreaturesCommonSpecsByType("WildBoar"));
    }
}
