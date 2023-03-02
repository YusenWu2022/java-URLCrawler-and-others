package finalTest;

class Test {
    public static void main(String[] s) {
        A a = new B();
        a.f(3);
        B b = new B();
        b.f(3);
    }
}

class A {
    void f(int a) {
        System.out.println("A-f " + a);
        if (a > 1)
            f(a - 1);
    }
}

class B extends A {
    void f(int a) {
        System.out.println("B-f " + a);
        if (a > 1)
            f(a - 1);
    }
}