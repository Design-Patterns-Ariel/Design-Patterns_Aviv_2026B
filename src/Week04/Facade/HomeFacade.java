package Facade;

public class HomeFacade extends Home{


    public HomeFacade(Room[] rooms) {
        super(rooms);
    }

    public void sleep()
    {




    }


    public void exit(){
        System.out.println("You have selected exit mode from home");
        for (int i = 0; i < getRooms().length; i++) {
            getRooms()[i].CloseTheAir(true);
            getRooms()[i].CloseTheTV(true);
            getRooms()[i].CloseTheWindow(true);
            getRooms()[i].CloseTheLight(true);
            getRooms()[i].CloseTheDoor(true);
        }

        CloseTheAir(true);
        CloseTheTV(true);
        CloseTheWindow(true);
        CloseTheLight(true);
        CloseTheDoor(true);
        System.out.println("Ok");


    }
}
