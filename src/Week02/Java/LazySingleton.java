package Week02.Java;

public class LazySingleton {

    private static int COUNT = 0;
    private static LazySingleton INSTANCE = null;


    public static LazySingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LazySingleton();
        return INSTANCE;
    }

    private LazySingleton() {
        LazySingleton.COUNT++;

    }

    @Override
    public String toString() {
        return super.toString() + " - LazySingleton{} -> " + COUNT;
    }
}
