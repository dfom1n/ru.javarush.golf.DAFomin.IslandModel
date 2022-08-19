package islandModel.services;

import islandModel.island.Island;
import islandModel.island.Location;
import islandModel.island.LocationWorker;

public class TaskLocation {
    private final Island island;
    private final Location location;
    private final LocationWorker locationWorker;

    public TaskLocation(Island island, Location location) {
        this.island = island;
        this.location = location;
        this.locationWorker = new LocationWorker();
    }

    public void perform() {
        if (location != null) {
            if (locationWorker.eatingHerbivoreSafe(location)) {

                locationWorker.breedingSafe(location);
            }
//            if (locationWorker.eatingPredatorSafe(location) & locationWorker.eatingHerbivoreSafe(location)) {
//
//                locationWorker.breedingSafe(location);
//            }
            locationWorker.eatingPredatorSafe(location);
            locationWorker.starveAnimalSafe(location);
            locationWorker.movingSafe(island, location);
        }
//
//        else {
//            organism.growUp(area);
//            organism.multiply(area);
//        }
//        organism.starve(area);
    }

}
