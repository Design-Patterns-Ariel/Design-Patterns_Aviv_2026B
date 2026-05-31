package Week08.Observer;

public class Person implements IObserver {

    private String name;
    private String last_name;
    private float age;
    private String id;
    private char gender;
    private String phone;
    private String email;

    public Person(String name, String last_name, float age, String id, char gender, String phone, String email) {
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.id = id;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public Person(PersonBuild other) {
        this.name = other.name;
        this.last_name = other.last_name;
        this.age = other.age;
        this.id = other.id;
        this.gender = other.gender;
        this.phone = other.phone;
        this.email = other.email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String print() {
        return "Person{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static PersonBuild builder(String name, String last_name, String id) {
        return new PersonBuild(name, last_name, id);
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


    public static class PersonBuild {

        private String name;
        private String last_name;
        private float age;
        private String id;
        private char gender;
        private String phone;
        private String email;


        public PersonBuild(String name, String last_name, String id) {
            this.name = name;
            this.last_name = last_name;
            this.id = id;
        }


        public PersonBuild setGender(char gender) {
            this.gender = gender;
            return this;

        }

        public PersonBuild setAge(float age) {
            this.age = age;
            return this;
        }

        public PersonBuild setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public PersonBuild setEmail(String email) {
            this.email = email;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

        public Person build(ObserverSystem observerSystem) {
            Person p = new Person(this);
            observerSystem.attach(p);
            return p;
        }
    }
}