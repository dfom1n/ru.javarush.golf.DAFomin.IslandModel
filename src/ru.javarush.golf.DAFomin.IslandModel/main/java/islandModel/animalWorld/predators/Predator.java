package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;

public interface Predator {
    public default <T extends Animal> void eatAnimal(T predator, T victim) {
        double amountFoodToSatiate = predator.getAnimalCharacteristics().getAmountFoodToSatiate();
        double satiety = predator.getAnimalCharacteristics().getSatiety();
        double victimWeight = victim.getAnimalCharacteristics().getWeight();
        if ((amountFoodToSatiate - satiety) > 0) {
            predator.getAnimalCharacteristics().setSatiety(satiety + victimWeight);
            if (predator.getAnimalCharacteristics().getSatiety() > predator.getAnimalCharacteristics().getAmountFoodToSatiate()) {
                predator.getAnimalCharacteristics().setSatiety(predator.getAnimalCharacteristics().getAmountFoodToSatiate());
            }
        }
    }
}
