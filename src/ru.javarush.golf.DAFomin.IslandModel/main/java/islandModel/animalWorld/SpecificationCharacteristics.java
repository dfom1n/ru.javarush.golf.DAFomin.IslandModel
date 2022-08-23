package islandModel.animalWorld;

import islandModel.services.Settings;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class SpecificationCharacteristics {
    Settings settings = Settings.get();
    private String name;
    private String icon;
    private double weight;
    private int maxCountSpecies;
    private int maxSpeedToLocation;
    private double amountFoodToSatiate;
    private double satiety;

    public SpecificationCharacteristics(){

    }

    public SpecificationCharacteristics(String name, String icon, double weight, int maxCountSpecies, int maxSpeedToLocation, double amountFoodToSatiate, double satiety) {
        this.name = name;
        this.icon = icon;
        this.weight = weight;
        this.maxCountSpecies = maxCountSpecies;
        this.maxSpeedToLocation = maxSpeedToLocation;
        this.amountFoodToSatiate = amountFoodToSatiate;
        this.satiety = ThreadLocalRandom.current().nextDouble(satiety);
    }


    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxCountSpecies() {
        return maxCountSpecies;
    }

    public int getMaxSpeedToLocation() {
        return maxSpeedToLocation;
    }

    public double getAmountFoodToSatiate() {
        return amountFoodToSatiate;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificationCharacteristics that = (SpecificationCharacteristics) o;
        return Double.compare(that.weight, weight) == 0 && maxCountSpecies == that.maxCountSpecies && maxSpeedToLocation == that.maxSpeedToLocation && Double.compare(that.amountFoodToSatiate, amountFoodToSatiate) == 0 && Double.compare(that.satiety, satiety) == 0 && Objects.equals(name, that.name) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, icon, weight, maxCountSpecies, maxSpeedToLocation, amountFoodToSatiate, satiety);
    }
}
