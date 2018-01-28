package L03_IterableAndComparable.P08_PetClinic;

public class RoomImpl implements Room {
    private Pet pet;

    public RoomImpl(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return pet.toString();
    }
}
