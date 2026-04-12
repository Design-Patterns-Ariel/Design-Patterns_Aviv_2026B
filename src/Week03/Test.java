package Week03;

import java.util.Arrays;
import java.util.Random;

public class Main {
    static Random rd = new Random();

    static void main() {

        Person[] people = new Person[100];
        for (int i = 0; i < 100; i++) {
            people[i] = new Person("" + rd.nextInt(600), i + " " + rd.nextInt(205));
        }
        System.out.println(Arrays.toString(people));

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (Integer.parseInt(people[i].name) > Integer.parseInt(people[j].name)) {
                    Person temp = people[i];
                    people[i] = people[j];
                    people[j] = temp;
                }
            }
        }

        Arrays.sort(people);

    }
}
