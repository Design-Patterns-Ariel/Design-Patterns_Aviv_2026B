package Week08.Observer;


public class Student extends Person {


    public Student(String name, String last_name, float age, String id, char gender, String phone, String email) {
        super(name, last_name, age, id, gender, phone, email);
    }

    public Student(String name, String last_name, float age, String id, char gender, String phone, String email, ObserverSystem observerSystem) {
        super(name, last_name, age, id, gender, phone, email);
        observerSystem.attach(this);
    }

    @Override
    public void sendMsgWithSms(String msg) {
        System.out.println("Send sms  -> msg: " + msg + " " + getName() + " to: " + getPhone());
    }

    @Override
    public void sendMsgWithEmail(String msg) {
        System.out.println("Send Email -> msg: " + msg + " " + getName() + " to: " + getEmail());
    }

    @Override
    public void sendMsg(String msg) {
        sendMsgWithEmail(msg);
        sendMsgWithSms(msg);
    }


}