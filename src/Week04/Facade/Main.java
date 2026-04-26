package Facade;

public class Main {

    public static void main(String[] args) {
        Home home = new Home(new Room[]{new Room(), new Room(), new Room(), new Room()});

        for (int i = 0; i < home.getRooms().length; i++) {
            home.getRooms()[i].CloseTheAir(true);
            home.getRooms()[i].CloseTheTV(true);
            home.getRooms()[i].CloseTheWindow(true);
            home.getRooms()[i].CloseTheLight(true);
            home.getRooms()[i].CloseTheDoor(true);
        }

        home.CloseTheAir(true);
        home.CloseTheTV(true);
        home.CloseTheWindow(true);
        home.CloseTheLight(true);
        home.CloseTheDoor(true);
        System.out.println("Ok");


        HomeFacade homeFacade = new HomeFacade(new Room[]{new Room(), new Room(), new Room(), new Room()});
        homeFacade.exit();
        homeFacade.sleep();

    }


}

