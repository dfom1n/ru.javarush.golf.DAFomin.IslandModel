package islandModel.animalWorld.plant;

import java.util.concurrent.ThreadLocalRandom;

public class Plant {
    private double  weight;
    public Plant() {
        this.weight = ThreadLocalRandom.current().nextDouble(1d);
    }
    public double getWeight() {
        return weight;
    }

    public String plantName() {
        String fullName = this.getClass().getSimpleName();
//        String name = fullName.substring(fullName.lastIndexOf(".")+1);
        return fullName;
    }
}
