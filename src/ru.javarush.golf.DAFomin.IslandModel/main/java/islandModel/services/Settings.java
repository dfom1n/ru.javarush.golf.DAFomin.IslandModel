package islandModel.services;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import islandModel.animalWorld.SpecificationCharacteristics;
import islandModel.animalWorld.SpecificationCreaturesCharacteristics;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Settings {

    private static final String INIT_FILE = "islandModel/settingsFile.yaml";

    private static volatile Settings SETTINGS;
    //размер острова
    private int lengthIsland;
    private int widthIsland;
//    ПРОДОЛЖИТЕЛЬНОСТЬ ЖИЗНЕННОГО ЦИКЛА
    private int cycleDuration; // millis
// ОСТАНОВКА ПО ТАЙМ-АУТУ
    private Boolean stopOnTimeout;
//продолжительность игры
    private int gameDuration;
//    шанс Получить Еду
    private Map<String, Map<String, Integer>> chanceToGetEat;
//    общие характеристики организмов
    private Map<String, SpecificationCharacteristics> creaturesCommonSpecs;
//    Типы организмов
    private List<String> creaturesTypesList;
//    нежизнеспособный Весовой процент
    private int unviableWeight;


    public Settings() {
        try {
            URL resource = Settings.class.getClassLoader().getResource(INIT_FILE);
            ObjectReader objectReader = new YAMLMapper().readerForUpdating(this);
            if (Objects.nonNull(resource)) {
                objectReader.readValue(resource.openStream());
            }

            creaturesTypesList = new ArrayList<>(creaturesCommonSpecs.keySet());
        } catch (IOException e) {
            //TODO Coding. System.out here? Need move the output to View layer
            System.out.printf("Ошибка при чтении файла настроек init.yml: %s", e);
        }
    }

    public SpecificationCreaturesCharacteristics getCreaturesCommonSpecsByType(String creaturesTypes) {
        SpecificationCreaturesCharacteristics specificationCreaturesCharacteristics = null;
        if (creaturesTypes.equals("Plant")){
        specificationCreaturesCharacteristics = new SpecificationCreaturesCharacteristics(creaturesCommonSpecs.get(creaturesTypes), creaturesTypes);
        } else specificationCreaturesCharacteristics = new SpecificationCreaturesCharacteristics(creaturesCommonSpecs.get(creaturesTypes));
        return specificationCreaturesCharacteristics;
    }

    public int getLengthIsland() {
        return lengthIsland;
    }

    public int getWidthIsland() {
        return widthIsland;
    }

    public int getCycleDuration() {
        return cycleDuration;
    }

    public Boolean getStopOnTimeout() {
        return stopOnTimeout;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public Map<String, Map<String, Integer>> getChanceToGetEat() {
        return chanceToGetEat;
    }

    public Map<String, SpecificationCharacteristics> getCreaturesCommonSpecs() {
        return creaturesCommonSpecs;
    }

    public List<String> getCreaturesTypes() {
        return creaturesTypesList;
    }

    public int getUnviableWeight() {
        return unviableWeight;
    }


    public static Settings get() {
        Settings settings = SETTINGS;

        if (Objects.isNull(settings)) {
            synchronized (Settings.class) {
                if (Objects.isNull(settings = SETTINGS)) {
                    settings = SETTINGS = new Settings();
                }
            }
        }

        return settings;
    }
}

