package Week07.Clients;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    static void main(String[] args) {
        int port = 3000;
        String host = "localhost";

        if (args.length >= 2)
            host = args[1];
        if (args.length >= 3)
            port = Integer.parseInt(args[2]);

        try {

            Client c = new Client(host, port);
            System.out.println("Enter Name:");
            c.setName(sc.nextLine());
            c.createClient();
            c.run();

        } catch (InterruptedException i) {
            System.out.println(i.getMessage());
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
