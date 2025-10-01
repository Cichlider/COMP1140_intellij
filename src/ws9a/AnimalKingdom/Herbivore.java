package ws9a.AnimalKingdom;

public interface Herbivore extends Animal {
    default void eat(Plant plant) {
        System.out.println(getName()+" can probably eat "+plant.getName());
    }
}
