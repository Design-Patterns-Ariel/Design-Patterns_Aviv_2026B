package Week08.Observer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Person pTest = new Person.PersonBuild("Barak", "Sharabi", "123456789")
                .setAge(15).setEmail("").build();
        Person p = Person.builder("Barak", "Sharabi", "123456789")
                .setAge(15).setEmail("").build();
        ObserverSystem observerSystem = new ObserverSystem();
        observerSystem.attach(pTest);
        observerSystem.attach(p);

        Person p1 = Person.builder("Barak", "Sharabi", "123456789")
                .setAge(15).setEmail("").build(observerSystem);

        while (true) {
            System.out.println("Enter Msg:");
            String msg = new Scanner(System.in).nextLine();
            System.out.println("Enter Mode:");
            int mode = new Scanner(System.in).nextInt();
            observerSystem.setMsg(mode, msg);
            System.out.println("----------------------------------------------");
        }

    }
}
