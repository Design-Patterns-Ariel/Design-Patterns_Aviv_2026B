package Week08.Servers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler extends Thread {

    private Socket in;
    private String nameId;
    private final DataOutputStream out;
    private final BufferedReader inFromClient;

    public SocketHandler(Socket in) throws IOException {
        this.in = in;
        out = new DataOutputStream(in.getOutputStream());
        inFromClient = new BufferedReader(new InputStreamReader(in.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());



        try {
            while (true) {

                String from = inFromClient.readLine();
                System.out.println(from);
                Server.broadcast(from, this);
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }


    public void write(String msg) throws IOException {
        out.writeBytes(msg + "\n");
    }
}
