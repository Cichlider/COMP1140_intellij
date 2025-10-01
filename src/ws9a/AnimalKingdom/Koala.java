package ws9a.AnimalKingdom;

public class Koala implements Herbivore {
    @Override
    public String getName() {
        return "Koala";
    }


    public void eat(Eucalyptus eucalyptus) {
        System.out.println(getName()+" loves to eat "+eucalyptus.getName());
    }
}
