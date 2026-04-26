package Prototype;

interface protoType extends Cloneable{
    Animal clone();
}


public class Animal implements protoType {


    @Override
    public Animal clone() {
        return null;
    }
}

class Dog extends Animal implements protoType{

    String name;

    public Dog(Dog other) {
        this.name=other.name;
    }

    public Dog() {

    }

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public Animal clone() {
        return new Dog(this);
    }
}

class Fish extends Animal implements protoType{
    @Override
    public Animal clone() {
        return null;
    }

}

class Cat extends Animal implements protoType{
    @Override
    public Animal clone() {
        return null;
    }

}
