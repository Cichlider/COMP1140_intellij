package ws9a.AnimalKingdom;

public interface Animal {
    String getName();
    default void eat(Food food) {
        System.out.println(getName()+" cannot eat "+food.getName());
    }
}
