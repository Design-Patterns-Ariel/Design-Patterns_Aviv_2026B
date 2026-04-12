package Week02;

import Week02.Java.LazySingleton;

public class Dashboard {


    public void getSettings(){
        System.out.println(LazySingleton.getInstance().toString());


    }
}
