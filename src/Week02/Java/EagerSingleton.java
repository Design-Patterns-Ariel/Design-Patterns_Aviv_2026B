package Week02.Java;

public class EagerSingleton {

    private static int COUNT = 0;
    private static final EagerSingleton FINAL_INSTANCE = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return FINAL_INSTANCE;
    }


    private EagerSingleton() {
        EagerSingleton.COUNT++;
    }


    @Override
    public String toString() {
        return super.toString() + " - EagerSingleton{} -> " + COUNT;
    }
}
