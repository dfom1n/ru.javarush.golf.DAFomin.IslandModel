package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.predators.Predator;
import islandModel.services.Settings;

public class Mouse extends Animal implements Herbivore, Predator {
    public Mouse() {
        super(Settings.get().getCreaturesCommonSpecsByType("Mouse"));
    }
}
