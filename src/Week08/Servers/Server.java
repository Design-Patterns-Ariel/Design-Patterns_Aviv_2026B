package Week08.Servers;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static ServerSocket s = null;
    static List<SocketHandler> clientMap = new ArrayList<>();


    static void main(String[] args) {

        int port = 3000;
        if (args.length >= 2)
            port = Integer.parseInt(args[1]);

        try {
            s = new ServerSocket(port);
            System.out.println("Server is running on port " + port);
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
            try {
                SocketHandler socketHandler = new SocketHandler(in);
                clientMap.add(socketHandler);
                socketHandler.start();
            } catch (IOException io) {
                System.out.println(io.getMessage());
                //in.close();
            }

        }
    }

    static void broadcast(String msg, SocketHandler sender) throws IOException {
        for (int i = 0; i < clientMap.size(); i++) {
            SocketHandler socketHandler = clientMap.get(i);
            if (!socketHandler.equals(sender))
                socketHandler.write(msg);
        }

    }


}
