package Week07.Clients;

import Week06.SocketHandlerRunnable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // 1. בדיקת קלט מארח ופורט
    // אם מישהו רוצה להביא רק ארגומנט אחד איך נחליט האם זה פורט או כתובת מארחת?
    // צרו מחלקה שיוצרת לקוח חדש כלומר פותח סוקט לסרבר
    // תשפרו את תהליך היצירה על ידי שימוש בתבנית העיצוב בילדר
    // לקוח כמשתמש קצה צריך להיווצר פעם אחת בלבד
    // החליטו כיצד ליצור את זרם הקלט שלכם בצורה טובה ומתאימה לעקרונות העיצוב
    // רישום של לקוח לשרת לפני הצטרפות לשירות עם בדיקה של חד ערכיות

    private static Scanner sc = new Scanner(System.in);
    private int port;
    private String host;


    private String sts;
    private String sfs;

    private Socket client;
    private DataOutputStream out;
    private BufferedReader in;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public Client() {
        this.port = 3000;
        this.host = "localhost";
    }

    public void createClient() throws IOException {
        try {
            client = new Socket(host, port);

            out = new DataOutputStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        } catch (IOException e) {
            System.out.println(e.getMessage());
            //client.close();
        }

    }

    public void run() throws IOException, InterruptedException {

        Runnable runnableWrite = () -> {
            while (true) {
                System.out.println("Enter msg:");
                sts = sc.nextLine();
                try {
                    out.writeBytes(name + ": " + sts + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable runnableRead = () -> {
            while (true) {
                try {
                    sfs = in.readLine();
                    System.out.println(sfs);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t = new Thread(runnableRead);
        Thread t2 = new Thread(runnableWrite);
        t.start();
        t2.start();

        t.join();
        t2.join();

    }


}
