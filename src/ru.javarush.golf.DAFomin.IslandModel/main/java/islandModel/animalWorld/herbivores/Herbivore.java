package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.animalWorld.plant.Plant;

public interface Herbivore {
    public default <T extends Animal> void eatPlant(T animal, Plant plant) {
//        сколько нужно еды
        double amountFoodToSatiate = animal.getAnimalCharacteristics().getAmountFoodToSatiate();
//     сытость на сейчас
        double satiety = animal.getAnimalCharacteristics().getSatiety();
//        Вес растения
        double plantWeight = plant.getWeight();
        if ((amountFoodToSatiate - satiety) > 0) {
            animal.getAnimalCharacteristics().setSatiety(satiety + plantWeight);
            if(animal.getAnimalCharacteristics().getSatiety()>animal.getAnimalCharacteristics().getAmountFoodToSatiate()){
                animal.getAnimalCharacteristics().setSatiety(animal.getAnimalCharacteristics().getAmountFoodToSatiate());
            }
        }
    }
}
