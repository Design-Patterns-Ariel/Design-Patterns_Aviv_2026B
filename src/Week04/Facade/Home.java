package Facade;

class Home implements Action {

    private Room[] rooms;

    public Room[] getRooms() {
        return rooms;
    }

    public Home(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public String CloseTheDoor(boolean action) {
        if (action)
            return "The home door is closed";
        return "The home door is open";
    }

    @Override
    public String CloseTheAir(boolean action) {
        if (action)
            return "The home air is closed";
        return "The home air is open";
    }

    @Override
    public String CloseTheTV(boolean action) {
        if (action)
            return "The home tv is closed";
        return "The home tv is open";
    }

    @Override
    public String CloseTheLight(boolean action) {
        if (action)
            return "The home light is closed";
        return "The home light is open";
    }

    @Override
    public String CloseTheWindow(boolean action) {
        if (action)
            return "The home window is closed";
        return "The home window is open";
    }

}
