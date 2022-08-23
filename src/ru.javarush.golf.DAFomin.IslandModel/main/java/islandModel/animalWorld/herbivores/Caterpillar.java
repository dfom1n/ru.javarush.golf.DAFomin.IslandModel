package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Caterpillar extends Animal implements Herbivore{
    public Caterpillar() {
        super(Settings.get().getCreaturesCommonSpecsByType("Caterpillar"));
    }
}
