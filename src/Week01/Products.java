package Week01;

public class Products {

    static void main() {

//        Week01.Db db = new Week01.Db("127.0.0.1",3000);
        String products =  Shared.getDb().getAllProducts();
        
    }



}
