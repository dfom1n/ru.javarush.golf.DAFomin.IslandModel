package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.services.Settings;

public class Sheep extends Animal implements Herbivore{
    public Sheep() {
        super(Settings.get().getCreaturesCommonSpecsByType("Sheep"));
    }
}
