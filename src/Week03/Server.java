package Week03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {


    static void main() throws IOException {
        ServerSocket s = null;
        Map<String, SocketHandler> clientMap = new HashMap<>();


        try {
            s = new ServerSocket(3000);
            System.out.println("Server is running on port 3000");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            Socket in = null;
            try {
                in = s.accept();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            new SocketHandler(in).start();

        }
    }


}
