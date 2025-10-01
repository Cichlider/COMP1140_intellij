package ws9a.AnimalKingdom;

public class Mouse implements Meat, Herbivore {
    @Override
    public String getName() {
        return "Mouse";
    }

    public void eat(Cheese grass) {
        System.out.println(getName()+" loves to eat "+grass.getName());
    }
}
