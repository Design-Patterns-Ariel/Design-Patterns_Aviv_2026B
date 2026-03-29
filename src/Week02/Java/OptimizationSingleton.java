package Week02.Java;

public class LazySingleton {

    private static int COUNT = 0;
    private static LazySingleton INSTANCE = null;
    private static final LazySingleton FINAL_INSTANCE = new LazySingleton();
    private static final LazySingleton GET_FINAL_INSTANCE = LazySingleton.getInstance();


    public static LazySingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LazySingleton();
        return INSTANCE;
    }

    public static synchronized LazySingleton getInstanceOPT() {
        if (INSTANCE == null)
            INSTANCE = new LazySingleton();
        return INSTANCE;
    }

    private LazySingleton() {
        // ----------------------------------
//        if(INSTANCE!=null)
//            throw new RuntimeException();
        LazySingleton.COUNT++;
        //COUNT++;

    }


    @Override
    public String toString() {
        return super.toString() + " - Singleton{} -> " + COUNT;
    }
}
