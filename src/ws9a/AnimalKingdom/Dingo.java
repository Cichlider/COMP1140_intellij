package ws9a.AnimalKingdom;

public class Dingo implements Carnivore {
    @Override
    public String getName() {
        return "Dingo";
    }

    public void eat(Kangaroo kangaroo) {
        System.out.println(getName()+" loves to eat "+kangaroo.getName());
    }
}
