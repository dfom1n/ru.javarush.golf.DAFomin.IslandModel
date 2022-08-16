package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Caterpillar extends Animal implements Herbivore{
    public Caterpillar() {
        super(Settings.get().getCreaturesCommonSpecsByType("Caterpillar"));
//       super (0.01, 1000, 0, 0.2, ThreadLocalRandom.current().nextDouble(0.2));
    }
}
