package Week01;

public class Shared {

    private static  Db DB = new Db("127.0.0.1",3000);

    public static Db getDb(){
        return DB;
    }

}
