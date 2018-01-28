package L01_Interfaces.mood3;

public class Archangel extends BaseGameObject implements GameObject {
    private int mana;

    public Archangel(String username, int level, int mana) {
        super(username, level);
        this.mana = mana;
    }

    @Override
    protected String generateHashPassword() {
        return new StringBuilder(super.getUsername()).reverse().toString() + (super.getUsername().length())*21;
    }

    @Override
    public String toString() {
        return String.format("%s%s",super.toString(), (this.mana * super.getLevel()));
    }
}
