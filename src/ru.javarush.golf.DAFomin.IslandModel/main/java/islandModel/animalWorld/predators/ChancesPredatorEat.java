package islandModel.animalWorld.predators;

import islandModel.services.Settings;


import java.util.Iterator;
import java.util.Map;


public class ChancesPredatorEat {
        public int chanceToGetEatPredator (String predator, String victim) {
            int chanceToEat = 0;
            Settings settings = Settings.get();
            Map<String, Integer> chanceToGetEatMap = settings.getChanceToGetEat().get(predator);
            Iterator<Map.Entry<String, Integer>> chanceToGetEatIterator = chanceToGetEatMap.entrySet().iterator();
            while (chanceToGetEatIterator.hasNext()) {
                Map.Entry<String, Integer> chanceToGetEat = chanceToGetEatIterator.next();
                String foodType = chanceToGetEat.getKey();
                if (foodType.equals(victim)) {
                    chanceToEat = chanceToGetEat.getValue();
                }
            }
        return chanceToEat;
        }
}
