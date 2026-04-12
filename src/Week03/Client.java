package Week03;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    static void main() throws IOException {

        String sts = "";
        String sfs = "";

        Socket client = null;
        try {
            client = new Socket("localhost", 3000);
            Scanner sc = new Scanner(System.in);

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while (true) {

                System.out.println("Enter msg:");
                sts = sc.nextLine();

                out.writeBytes(sts + "\n");
                sfs = in.readLine();
                System.out.println(sfs);

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            client.close();
        }
    }
}
