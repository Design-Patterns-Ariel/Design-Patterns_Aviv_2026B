package Prototype;

import java.util.Stack;

public class PT {
    public static void main(String[] args) {

        A a = new A(1);
        A b = new B(1, 2);
        C c = new C(1, 2, 3);
        A d = new D(1, 2, 3, 4);

        B b1 = new B(5, 6);
        B c1 = new C(5, 6, 7);
        B d1 = new D(5, 6, 7, 8);

        C c2 = new C(9, 10, 11);
        C d2 = new D(9, 10, 11, 12);

        D d3 = new D(13, 14, 15, 16);

        a.func();
        b.func();
        c.func();
        d.func();

        b1.func();
        c1.func();
        d1.func();

        c2.func();
        d2.func();

        d3.func();

        A aCopy;
        if (a instanceof A)
            aCopy = new A(a.a);

        A cCopy;
        if (c instanceof A) {
            cCopy = new A(c.a);
        } else if (c instanceof B) {
            cCopy = new B(c.a, c.b);
        } else if (c instanceof C) {
            cCopy = new C(c.a, c.b, c.c);
        }


    }
}


class A {
    protected int a;

    public A(int a) {
        this.a = a;
    }
    protected void func() {
        System.out.println(this.getClass());
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                '}';
    }
}

class B extends A {
    protected int b;

    public B(int a, int b) {
        super(a);
        this.b = b;
    }


    protected void func() {
        System.out.println(this.getClass());
    }
}

class C extends B {
    public C(int a, int b, int c) {
        super(a, b);
        this.c = c;
    }

    protected int c;

    protected void func() {
        System.out.println(this.getClass());
    }
}

class D extends C {
    public D(int a, int b, int c, int d) {
        super(a, b, c);
        this.d = d;
    }

    protected int d;

    protected void func() {
        System.out.println(this.getClass());
    }
}
