package islandModel.animalWorld.predators;

import islandModel.animalWorld.Animal;

public interface Predator {
    public default <T extends Animal> void eatAnimal(T predator, T victim) {
//        сколько нужно еды
        double amountFoodToSatiate = predator.getAnimalCharacteristics().getAmountFoodToSatiate();
////        сытость на сейчас
        double satiety = predator.getAnimalCharacteristics().getSatiety();
//        Вес жертвы
        double victimWeight = victim.getAnimalCharacteristics().getWeight();
        if ((amountFoodToSatiate - satiety) > 0) {
            predator.getAnimalCharacteristics().setSatiety(satiety + victimWeight);
            if(predator.getAnimalCharacteristics().getSatiety()>predator.getAnimalCharacteristics().getAmountFoodToSatiate()){
                predator.getAnimalCharacteristics().setSatiety(predator.getAnimalCharacteristics().getAmountFoodToSatiate());
            }
        }
    }
}
