package Week08.Observer;
public interface IObserverSystem {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyMsg(int mode,String msg);
    void notifyMsg(String msg);
    void setMsg(String msg);
    void setMsg(int mode,String msg);
}
