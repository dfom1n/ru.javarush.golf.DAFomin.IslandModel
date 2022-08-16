package islandModel.animalWorld;

import islandModel.animalWorld.herbivores.Caterpillar;
import islandModel.island.*;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Animal {
    private SpecificationCreaturesCharacteristics animalCharacteristics;

    public Animal(SpecificationCreaturesCharacteristics animalCharacteristics) {
        this.animalCharacteristics = animalCharacteristics;
    }

    public SpecificationCreaturesCharacteristics getAnimalCharacteristics() {
        return animalCharacteristics;
    }

    public directionMovement directionMovementRandom() {
        return directionMovement.values()[ThreadLocalRandom.current().nextInt(directionMovement.values().length - 1)];
    }

    public Integer movementSpeedRandom(){
        if(this instanceof Caterpillar){
            return 0;
        } else
        return ThreadLocalRandom.current().nextInt(1,this.animalCharacteristics.getMaxSpeedToLocation()+1);
    }
//Лучше стоит мув перекинуть в клас лакатион
    public boolean move(Coordinates coordinates, Island island) {
        Location locationMove = null;
        directionMovement directionMovement = directionMovementRandom();
        int moveSpeedRandom = movementSpeedRandom();
//        берем координыты действующей локации
        int oldLocationX = coordinates.getOrdinateX();
        int oldLocationY = coordinates.getOrdinateY();
//        ищем координыты новой локации (подставляем к к координатам действующей локации енам)
        int locationMoveX = oldLocationX + (directionMovement.getX()*moveSpeedRandom);
        int locationMoveY = oldLocationY + (directionMovement.getY()*moveSpeedRandom);
//        если локация с новыми координатами существует
        if((oldLocationX ==locationMoveX && oldLocationY==locationMoveY) || (locationMoveX<0 || locationMoveY <0)){
            locationMove= null;
        } else if (island.getSizeIsland().getLength()-1 >= locationMoveX && island.getSizeIsland().getWidth()-1 >= locationMoveY) {
            locationMove = island.moveToLocation(locationMoveX, locationMoveY);
            locationMove.getLocationListAnimal().setAnimal(this);
        }
        if (locationMove!= null){
            return true;
        } else return false;
    }

    public String animalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalCharacteristics, animal.animalCharacteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalCharacteristics);
    }
}
