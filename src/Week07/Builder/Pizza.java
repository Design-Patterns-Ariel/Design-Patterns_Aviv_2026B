package Week07.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pizza {

    private String size;
    private List<String> toppings;
    private String sauceType;
    private String doughType;
    private int cheese;

    public Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.toppings = builder.toppings;
        this.sauceType = builder.sauceType;
        this.doughType = builder.doughType;
        this.cheese = builder.cheese;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", toppings=" + toppings +
                ", sauceType='" + sauceType + '\'' +
                ", doughType='" + doughType + '\'' +
                ", cheese=" + cheese +
                '}';
    }

    public static class PizzaBuilder {

        private String size;
        private List<String> toppings = new ArrayList<>();
        private String sauceType;
        private String doughType;
        private int cheese;

        public PizzaBuilder() {
        }

        public PizzaBuilder(String size) {
            this.size = size;
        }

        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setToppings(String toppings) {
            this.toppings.add(toppings);
            return this;
        }

        public PizzaBuilder setSauceType(String sauceType) {
            this.sauceType = sauceType;
            return this;
        }

        public PizzaBuilder setDoughType(String doughType) {
            this.doughType = doughType;
            return this;
        }

        public PizzaBuilder setCheese(String flag) {
            switch (flag) {

                case "+":
                    this.cheese += 10;
                    break;
                case "-":
                    this.cheese -= 10;
                    break;
                default:
                    this.cheese = cheese;
            }
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}


class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();

        System.out.println("Enter pizza size (small, lg, md)");
        String size = sc.nextLine();
        builder.setSize(size);

        System.out.println("Enter pizza sauce type (regular, bbq)");
        String sauceType = sc.nextLine();
        builder.setSauceType(sauceType);

        System.out.println("Enter pizza dough type (regular, gluten-free)");
        String doughType = sc.nextLine();
        builder.setDoughType(doughType);

        System.out.println("Add toppings (type done for finish)");
        while (true) {
            String topping = sc.nextLine();
            if (topping.equals("done")) {
                break;
            }
            builder.setToppings(topping);
        }

        System.out.println("Add cheese (type done for finish)");
        while (true) {
            String c = sc.nextLine();
            if (c.equals("done")) {
                break;
            }
            builder.setCheese(c);
        }

        Pizza pizza = builder.build();

        System.out.println(pizza);

        Pizza.PizzaBuilder builder1 = new Pizza.PizzaBuilder("XL");

        System.out.println("Add toppings (type done for finish)");
        while (true) {
            String topping = sc.nextLine();
            if (topping.equals("done")) {
                break;
            }
            builder1.setToppings(topping);
        }

        System.out.println("Add cheese (type done for finish)");
        while (true) {
            String c = sc.nextLine();
            if (c.equals("done")) {
                break;
            }
            builder1.setCheese(c);
        }

        Pizza pizza1 = builder.build();

        System.out.println(pizza1);
    }
}