package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Sheep extends Animal implements Herbivore{
    public Sheep() {
//        super(70, 140, 3, 15,
//                ThreadLocalRandom.current().nextDouble(1,15));
        super(Settings.get().getCreaturesCommonSpecsByType("Sheep"));
    }
}
