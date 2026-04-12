package Week03;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler extends Thread {

    private Socket in;
    private String nameId;

    public SocketHandler(Socket in) {
        this.in = in;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            DataOutputStream out = new DataOutputStream(in.getOutputStream());
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in.getInputStream()));
            out.writeBytes("" + "\n");

            while (true) {

                String from = inFromClient.readLine();
                System.out.println(from);
                out.writeBytes("Hello" + "\n");

            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
