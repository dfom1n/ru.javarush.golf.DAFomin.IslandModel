package islandModel.services;

import islandModel.island.Island;
import islandModel.island.Location;

public class TaskLocation {
    private final Island island;
    private final Location location;

    public TaskLocation(Island island, Location location) {
        this.island = island;
        this.location = location;
    }

    public void perform() {
//        if (location != null) {
            if (location.eatingPredator()&& location.eatingHerbivore()) {
                location.breeding();
            }
            location.starveAnimal();
            location.moving(island);
//        }
//
//        else {
//            organism.growUp(area);
//            organism.multiply(area);
//        }
//        organism.starve(area);
    }

}
