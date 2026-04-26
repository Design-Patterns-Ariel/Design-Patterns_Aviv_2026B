package Facade;

class Room implements Action {

    @Override
    public String CloseTheDoor(boolean action) {
        if (action)
            return "The door is closed";
        return "The door is open";
    }

    @Override
    public String CloseTheAir(boolean action) {
        if (action)
            return "The air is closed";
        return "The air is open";
    }

    @Override
    public String CloseTheTV(boolean action) {
        if (action)
            return "The tv is closed";
        return "The tv is open";
    }

    @Override
    public String CloseTheLight(boolean action) {
        if (action)
            return "The light is closed";
        return "The light is open";
    }

    @Override
    public String CloseTheWindow(boolean action) {
        if (action)
            return "The window is closed";
        return "The window is open";
    }
}
