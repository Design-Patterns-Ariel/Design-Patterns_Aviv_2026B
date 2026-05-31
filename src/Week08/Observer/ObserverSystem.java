package Week08.Observer;


import java.util.LinkedList;
import java.util.List;

public class ObserverSystem implements IObserverSystem {

    private List<IObserver> observers;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyMsg(msg);
    }

    public void setMsg(int mode, String msg) {
        this.msg = msg;
        notifyMsg(mode, msg);
    }

    public ObserverSystem() {
        this.observers = new LinkedList<>();
    }


    @Override
    public void attach(IObserver observer) {
        if (!observers.contains(observer))
            this.observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        if (observers.contains(observer))
            this.observers.remove(observer);
    }


    @Override
    public void notifyMsg(int mode, String msg) {

        for (IObserver obs : this.observers) {

            switch (mode) {
                case 0:
                    obs.sendMsg(msg);
                    break;
                case 1:
                    obs.sendMsgWithEmail(msg);
                    break;
                case 2:
                    obs.sendMsgWithSms(msg);
                    break;
                default:
                    System.out.println("Error");
            }

        }

    }

    @Override
    public void notifyMsg(String msg) {

        for (IObserver obs : this.observers) {
            obs.sendMsg(msg);
        }

    }
}
