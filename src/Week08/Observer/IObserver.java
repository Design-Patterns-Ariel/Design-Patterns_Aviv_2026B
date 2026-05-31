package Week08.Observer;

public interface IObserver {
    void sendMsgWithSms(String msg);
    void sendMsgWithEmail(String msg);
    void sendMsg(String msg);

}