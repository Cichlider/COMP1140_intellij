package ws9a;

import org.junit.jupiter.api.Test;
import ws9a.AnimalKingdom.*;

public class AnimalKingdomTests {

    @Test
    void testKoala() {
        Koala koala=new Koala();
        Eucalyptus eucalyptus=new Eucalyptus();
        Cheese cheese = new Cheese();
        Mouse mouse = new Mouse();
        koala.eat(eucalyptus);
        koala.eat(cheese);
        koala.eat(mouse);
    }

    @Test
    void testKoala2() {
        Herbivore koala = new Koala();
        Eucalyptus eucalyptus=new Eucalyptus();
        Cheese cheese = new Cheese();
        Mouse mouse = new Mouse();
        koala.eat(eucalyptus);
        koala.eat(cheese);
        koala.eat(mouse);
    }

    @Test
    void testKoala3() {
        Animal koala = new Koala();
        Eucalyptus eucalyptus=new Eucalyptus();
        Cheese cheese = new Cheese();
        Mouse mouse = new Mouse();
        koala.eat(eucalyptus);
        koala.eat(cheese);
        koala.eat(mouse);
    }

    @Test
    void testDingo() {
        Dingo dingo = new Dingo();
        Kangaroo kangaroo = new Kangaroo();
        Mouse mouse = new Mouse();
        Grass grass=new Grass();
        dingo.eat(kangaroo);
        dingo.eat(mouse);
        dingo.eat(grass);
    }

    @Test
    void testDingo2() {
        Dingo dingo = new Dingo();
        Meat kangaroo = new Kangaroo();
        Meat mouse = new Mouse();
        Plant grass=new Grass();
        dingo.eat(kangaroo);
        dingo.eat(mouse);
        dingo.eat(grass);
    }

    @Test
    void testDingo3() {
        Dingo dingo = new Dingo();
        Food kangaroo = new Kangaroo();
        Food mouse = new Mouse();
        Food grass=new Grass();
        dingo.eat(kangaroo);
        dingo.eat(mouse);
        dingo.eat(grass);
    }
}
