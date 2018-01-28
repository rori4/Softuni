package L01_Interfaces.mood3;

public abstract class BaseGameObject implements GameObject {
    private String username;
    private String hashedPassword;
    private int level;

    public BaseGameObject(String username, int level) {
        this.username = username;
        this.hashedPassword = this.generateHashPassword();
        this.level = level;
    }

    protected String getUsername() {
        return this.username;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public int getLevel() {
        return this.level;
    }

    protected abstract String generateHashPassword();

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s\n", this.username, this.hashedPassword, this.getClass().getSimpleName());
    }
}
