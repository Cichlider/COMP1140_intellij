package ws9a.AnimalKingdom;

public class Cat implements Carnivore {
    @Override
    public String getName() {
        return "Cat";
    }

    public void eat(Mouse mouse) {
        System.out.println(getName()+" loves to eat "+mouse.getName());
    }
}
