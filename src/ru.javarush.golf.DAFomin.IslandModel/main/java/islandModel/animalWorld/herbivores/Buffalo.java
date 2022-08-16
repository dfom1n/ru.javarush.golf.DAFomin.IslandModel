package islandModel.animalWorld.herbivores;

import islandModel.animalWorld.Animal;
import islandModel.island.Settings;
import jdk.jfr.SettingControl;

import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends Animal implements Herbivore{

    public Buffalo() {
        super(Settings.get().getCreaturesCommonSpecsByType("Buffalo"));
//        super(700,
//                10,
//                3,
//                100,
//                ThreadLocalRandom.current().nextDouble(5,100));


    }
}
