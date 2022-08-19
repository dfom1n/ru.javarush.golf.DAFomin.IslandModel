package islandModel;

import islandModel.animalWorld.herbivores.Caterpillar;
import islandModel.island.Coordinates;
import islandModel.island.Initializer;
import islandModel.island.Island;
import islandModel.island.Location;
import islandModel.services.SimulationIslandLife;
//import islandModel.services.SimulationLocationLife;

public class Runner {
    public static void main(String[] args) {
        Initializer initializer = new Initializer();
        Island island = initializer.createIsland();
        SimulationIslandLife simulationIslandLife = new SimulationIslandLife(island);
        simulationIslandLife.start();
    }
}
