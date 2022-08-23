package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Buffalo extends Animal implements Herbivore{

    public Buffalo() {
        super(Settings.get().getCreaturesCommonSpecsByType("Buffalo"));
    }
}
