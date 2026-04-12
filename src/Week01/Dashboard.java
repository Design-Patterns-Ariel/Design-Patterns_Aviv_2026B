package Week01;

public class Dashboard {

    static void main() {
        Db db = new Db("127.0.0.1",3000);
        String users = db.getAllUser();
    }

}
