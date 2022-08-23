package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.predators.Predator;
import islandModel.services.Settings;

public class WildBoar extends Animal implements Herbivore, Predator {
    public WildBoar() {
        super(Settings.get().getCreaturesCommonSpecsByType("WildBoar"));
    }
}
