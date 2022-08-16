package islandModel.animalWorld.predators;

import islandModel.island.Settings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChancesPredatorEat {
//        private String filePathInput;
//        public ChancesPredatorEat(){
//                setFilePathInput("/Users/19823162/IdeaProjects/ru.javarush.golf.DAFomin.islandModel/src/ru.javarush.golf.DAFomin.islandModel/main/resources/theChancesOfPredatorEatingVictim.txt");
//        }
//        public String getFilePathInput() {
//            return filePathInput;
//        }
//        private void setFilePathInput(String filePathIn) {
//            this.filePathInput = filePathIn;
//        }

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
