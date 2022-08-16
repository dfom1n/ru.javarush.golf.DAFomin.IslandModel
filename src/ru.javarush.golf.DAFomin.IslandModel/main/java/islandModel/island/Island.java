package islandModel.island;

import islandModel.animalWorld.herbivores.Caterpillar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Island {
    SizeIsland sizeIsland = new SizeIsland();
    Location[][] locations = new Location[sizeIsland.getLength()][sizeIsland.getWidth()];

    public void initialize() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(new Coordinates(i, j));
            }
        }
    }

    public void print() {
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                System.out.println(locations[i][j].toString());
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public List<Location> getLocationList() {
        List<Location> locationsList = new ArrayList<>();
        for (Location[] i : locations) {
            for (Location j : i) {
                locationsList.add(j);
            }
        }
        return locationsList;
    }

    public Location moveToLocation(int x, int y) {
        List<Location> locationMoveList;
        Location locationMove = null;
        locationMoveList = getLocationList().stream().filter(loc -> loc.getCoordinates().getOrdinateX() == x && loc.getCoordinates().getOrdinateY() == y).collect(Collectors.toList());
        if (locationMoveList.size() == 1) {
            locationMove = locationMoveList.get(0);
        }
        return locationMove;
    }

    public Location getLocationRandom() {
        List<Location> locationRandomList = new ArrayList<>();
        Location locationRandom = null;
        locationRandomList = getLocationList().stream().filter(loc -> loc.getCoordinates().getOrdinateX() == ThreadLocalRandom.current().nextInt(sizeIsland.getLength() - 1) && loc.getCoordinates().getOrdinateY() == ThreadLocalRandom.current().nextInt(sizeIsland.getWidth()) - 1).collect(Collectors.toList());
        if (locationRandomList.size() == 1) {
            locationRandom = locationRandomList.get(0);
        }

        return locationRandom;
    }

    public SizeIsland getSizeIsland() {
        return sizeIsland;
    }

    public Location getLocation(Coordinates coordinates) {
        Location location = null;
        for (int i = 0; i < locations.length; i++) {
            if (i == coordinates.getOrdinateX()) {
                for (int j = 0; j < locations[i].length; j++) {
                    if (j == coordinates.getOrdinateY()) {
                        location = locations[i][j];
                        break;
                    }
                }
            }
        }
        return location;
    }

    public Location[][] getLocations() {
        return locations;
    }
}

