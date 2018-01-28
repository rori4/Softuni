package L01_Interfaces.foodShortage;

public class Robot extends BaseResident {
    private String model;

    public Robot(String model, String id) {
        super(id);
        this.model = model;
    }
}
