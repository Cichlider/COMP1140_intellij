package ws9a.AnimalKingdom;

public interface Carnivore extends Animal {
    default void eat(Meat meat) {
        System.out.println(getName()+" can probably eat "+meat.getName());
    }
}
