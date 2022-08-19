package islandModel.animalWorld.plant;

import islandModel.animalWorld.SpecificationCreaturesCharacteristics;
import islandModel.island.Settings;

import java.util.concurrent.ThreadLocalRandom;

public class Plant {
//    private double  weight;
//    public Plant() {
//        this.weight = ThreadLocalRandom.current().nextDouble(1d);
//    }
//    public double getWeight() {
//        return weight;
//    }
private SpecificationCreaturesCharacteristics plantCharacteristics;

    public Plant() {
        this.plantCharacteristics = Settings.get().getCreaturesCommonSpecsByType("Plant");
    }

    public SpecificationCreaturesCharacteristics getAnimalCharacteristics() {
        return plantCharacteristics;
    }





    public String plantName() {
        String fullName = this.getAnimalCharacteristics().getIcon();
//        String name = fullName.substring(fullName.lastIndexOf(".")+1);
        return fullName;
    }
}
