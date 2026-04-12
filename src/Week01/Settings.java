package Week01;

public class Settings {

    static void main() {
//        Week01.Db db = new Week01.Db("127.0.0.1",3000);
        String user = Shared.getDb().getUser("Artur");
    }


}
