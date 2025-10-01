package ws9a.AnimalKingdom;

public class Kangaroo implements Herbivore, Meat {
    @Override
    public String getName() {
        return "Kangaroo";
    }

    public void eat(Grass grass) {
        System.out.println(getName()+" loves to eat "+grass.getName());
    }
}
