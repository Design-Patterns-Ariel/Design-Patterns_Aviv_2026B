package Week02.Java;

public class Singleton {

    private static int COUNT = 0;
    private static Singleton INSTANCE = null;
    private static final Singleton FINAL_INSTANCE = new Singleton();
    private static final Singleton GET_FINAL_INSTANCE = Singleton.getInstance();


    public static Singleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Singleton();
        return INSTANCE;
    }

    public static synchronized Singleton getInstanceOPT() {
        if (INSTANCE == null)
            INSTANCE = new Singleton();
        return INSTANCE;
    }

    private Singleton() {
        // ----------------------------------
//        if(INSTANCE!=null)
//            throw new RuntimeException();
        Singleton.COUNT++;
        //COUNT++;

    }


    @Override
    public String toString() {
        return super.toString() + " - Singleton{} -> " + COUNT;
    }
}
