package Week02.Java;

public class OptimizationSingleton {

    private static int COUNT = 0;
    private static OptimizationSingleton INSTANCE = null;


    public static synchronized OptimizationSingleton getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OptimizationSingleton();
        return INSTANCE;
    }

    private OptimizationSingleton() {
        // ----------------------------------
//        if(INSTANCE!=null)
//            throw new RuntimeException();
        OptimizationSingleton.COUNT++;
        //COUNT++;

    }


    @Override
    public String toString() {
        return super.toString() + " - OptimizationSingleton{} -> " + COUNT;
    }
}
