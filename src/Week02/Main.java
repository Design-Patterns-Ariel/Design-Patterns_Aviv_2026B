package Week02;

import Week02.Java.LazySingleton;

public class Main {

    static void main() throws Exception {
        for (int i = 0; i < 10; i++)
            System.out.println(LazySingleton.getInstance());


//        for (int i = 0; i < 10; i++)
//            System.out.println(new Singleton().toString());

        new Dashboard().getSettings();
        new Dashboard().getSettings();
        new Dashboard().getSettings();
        new Dashboard().getSettings();
        new Dashboard().getSettings();

    }
}
