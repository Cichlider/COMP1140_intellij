package ws8a;

import java.util.Date;
import java.util.Optional;

public class UniversityUsingAbsClass {
    public static void main(String[] args) {
        // Cannot do it because UserAbsClass is an Abstract Class
        // var u = new UserAbsClass("user");
        var s = new Student2("John");
        var student_from_interface = new Student("interface");
        /*
         * You do not need to understand how Date works
         * year is 117 because it is the number of years after 1900 (2017 = 1900 + 117)
         */
        var a = new Academic2("Felipe", new Date(117, 1, 1));
        System.out.println(s + " -- next pay raise: " + s.nextSalaryIncrease());
        System.out.println(a + " -- next pay raise: " + a.nextSalaryIncrease());
        // does not work because id is final
        // a.id = 19;
        System.out.println(a.name);
    }
}

abstract class UserAbsClass {
    protected String name;
    public final int id;
    private static int counter = 0;
    UserAbsClass(String name) {
        this.name = name;
        this.id = counter++;
    }
    /*
     * Optional is the Java version of Maybe<> from Functional Java
     * More details here: https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
     */
    abstract Optional<Date> nextSalaryIncrease();
    @Override
    public String toString() {
        return name + " / " + super.toString();
    }
}

class Student2 extends UserAbsClass {
    Student2(String name) {
        super(name);
    }

    @Override
    Optional<Date> nextSalaryIncrease() {
        return Optional.empty();  // same as return Nothing in functional java
    }

    @Override
    public String toString() {
        return super.toString() + " (is a student)";
    }
}

class Academic2 extends UserAbsClass {
    Date hired;
    Academic2(String name, Date hired) {
        super(name);
        this.hired = hired;
    }

    @Override
    Optional<Date> nextSalaryIncrease() {
        /*
         * You don't need to understand how Date and the logic used here
         */
        Date today = new Date();
        Date next = new Date(hired.getTime());
        // Keep adding years until it's after today
        while (!next.after(today)) {
            next.setYear(next.getYear() + 1);
        }
        return Optional.of(next);  // same as Something from Functional Java
    }

    @Override
    public String toString() {
        return super.toString() + " (hired on " + hired + ")";
    }
}