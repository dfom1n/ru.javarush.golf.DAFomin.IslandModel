package islandModel.animalWorld.plant;

import islandModel.animalWorld.SpecificationCreaturesCharacteristics;
import islandModel.services.Settings;

public class Plant {
private SpecificationCreaturesCharacteristics plantCharacteristics;

    public Plant() {
        this.plantCharacteristics = Settings.get().getCreaturesCommonSpecsByType("Plant");
    }

    public SpecificationCreaturesCharacteristics getAnimalCharacteristics() {
        return plantCharacteristics;
    }

    public String plantName() {
        String fullName = this.getAnimalCharacteristics().getIcon();
        return fullName;
    }
}
