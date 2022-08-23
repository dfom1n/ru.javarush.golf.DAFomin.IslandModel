package islandModel.services;

import islandModel.animalWorld.Animal;
import islandModel.island.Island;
import islandModel.island.Location;

public class TaskLocation<T extends Animal> {
    private final Island island;
    private final Location location;
    private final T animal;

    public TaskLocation(Island island, Location location, T animal) {
        this.island = island;
        this.location = location;
        this.animal = animal;
    }

    public void perform() {
        if (location != null && animal != null) {
            animal.eatingHerbivore(location);
            animal.eatingPredator(location);
            animal.breeding(location);
            animal.starveAnimal(location);
            animal.moving(island, location);
        }

    }
}
