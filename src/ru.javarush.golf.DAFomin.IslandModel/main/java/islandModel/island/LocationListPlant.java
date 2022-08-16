package islandModel.island;

import islandModel.animalWorld.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LocationListPlant<T extends Plant> {
    private double maxCountPlantLocation = 200d;
    private final List<T> listPlantDefault = new ArrayList<>();
    private List<T> listPlant = new ArrayList<>();
    private double countAnimalsLocation;
    private T plant;

    public LocationListPlant() {
        listPlantDefault.add((T) new Plant());
        this.countAnimalsLocation = ThreadLocalRandom.current().nextDouble(maxCountPlantLocation);
    }

    public <T extends Plant> void creatingPlantRandom() {
        while (listPlant.size() < countAnimalsLocation) {
            plant = listPlantDefault.get(ThreadLocalRandom.current().nextInt(listPlantDefault.size()));
            if (maxCountPlantLocation > listPlant.stream().filter(a -> a.getClass().getName().equals(plant.getClass().getName())).count()) {
                if (plant instanceof Plant) {
                    setPlant(new Plant());
                }
            }
        }
    }

    public void setPlant(Plant plant) {
        listPlant.add((T) plant);
    }

    public List<T> getListPlant() {
        return listPlant;
    }

}

