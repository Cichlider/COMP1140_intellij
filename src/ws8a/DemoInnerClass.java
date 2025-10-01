package ws8a;

public class DemoInnerClass {
    public static void main(String[] args) {
    var b = new B("private string");
    var a = b.getA();
    System.out.println(a.getString());
    var d = new B.D(b);
    System.out.println(d.getString());
}
}

interface A { String getString(); }

class B {
    private String str;
    B(String s) { str = s; }
    private class C implements A {
        @Override
        public String getString() {
            return str;
        }
    }
    A getA() { return new C(); }

    public static class D implements A {
        B b;
        D(B b) {
            this.b = b;
        }
        @Override
        public String getString() {
            return b.str;
        }
    }
}