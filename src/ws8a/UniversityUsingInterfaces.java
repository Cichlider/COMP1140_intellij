package ws8a;

public class UniversityUsingInterfaces {
    public static void main(String[] args) {
        var s1 = new Student("John");
        var a1 = new Academic("Felipe");
        System.out.println("Student: " + s1.getName());
        System.out.println("Academic: " + a1.getName());
    }
}

interface User {
    String getName();
}

class Student implements User {
    String name;
    Student(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}

class Academic implements User {
    String name;
    Academic(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}