package Prototype;

public class CopyConstructor {


    public static void main(String[] args) {
        Date dEngine = new Date("14:00","09.06.2024");
        Engine e = new Engine(dEngine);
        Date dCar = new Date("14:00","09.06.2023");
        Car c = new Car(e,0,"red",dCar);
        System.out.println(c);
        System.out.println("---------------------\n");
        Car cFaild =  c;
        System.out.println("\n---------------------\n");
        System.out.println(c);
        System.out.println(cFaild);

        System.out.println("\n----------change color-----------\n");
        c.color="blue";

        System.out.println(c);
        System.out.println(cFaild);

        System.out.println("\n---------------------\n");


       Car cF2 = new Car(c.e, c.price, c.color,c.date);
        System.out.println("\n---------------------\n");
        System.out.println(c);
        System.out.println(cF2);
        System.out.println("\n----------change color-----------\n");
        c.color="red";

        System.out.println(c);
        System.out.println(cF2);
        System.out.println("\n---------------------\n");

        System.out.println("\n----------change HP-----------\n");
        c.e.HP=120;

        System.out.println(c);
        System.out.println(cF2);
        System.out.println("\n---------------------\n");
        Car cF3 = new Car(new Engine(c.date),c.price,c.color,c.date);

        System.out.println(c);
        System.out.println(cF3);


        Car cCopy = new Car(c);

        System.out.println(c);
        System.out.println(cCopy);



    }

}

class Car{
    Engine e;
    float price;
    String color;
    Date date;

    public Car(Engine e, float price, String color, Date date) {
        this.e = e;
        this.price = price;
        this.color = color;
        this.date = date;
    }

    public Car(Car other) {
        this.color= other.color;
        this.price= other.price;
        this.e= new Engine(other.e);
        this.date= new Date(other.date);


    }

    @Override
    public String toString() {
        return "Car{" +
                "e=" + e +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", date=" + date +
                '}' + super.toString();
    }
}

class Date{
    String time;
    String year;

    public Date(String time, String year) {
        this.time = time;
        this.year = year;
    }

    public Date(Date other) {
        this.time= other.time;
        this.year= other.year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "time='" + time + '\'' +
                ", year='" + year + '\'' +
                '}'+super.toString();
    }
}

class Engine{
    Date date;
    int HP=150;

    public Engine(Date date) {
        this.date = date;
    }

    public Engine(Engine other) {
        this.HP= other.HP;
        this.date = new Date(other.date);
    }

    @Override
    public String toString() {
        return "Engine{" +
                "date=" + date +
                ", HP=" + HP +
                '}'+super.toString();
    }
}


