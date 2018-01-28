package L01_Interfaces.birthdayCelebrations;
public class Pet implements Birthable{
    private String name;
    private String birthday;

    protected Pet(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }
}
