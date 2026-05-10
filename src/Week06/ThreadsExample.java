package Week06;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ThreadsExample {


    static void main() throws Exception {

        Runnable runnableLambda = () -> {
            while (true) {
                System.out.println("Hello");
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Hello");
                }
            }
        };

        Thread t = new Thread(runnable);
        Thread t2 = new Thread(new SocketHandlerRunnable());
        t.start();
        t2.start();

        t.join();
        t2.join();
    }
}
