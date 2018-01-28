package L01_Interfaces.birthdayCelebrations;

public class Citizen extends BaseResident implements Birthable{
    private String name;
    private int age;
    private String birthday;

    public Citizen(String name, int age, String id, String birthday) {
        super(id);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }
}
