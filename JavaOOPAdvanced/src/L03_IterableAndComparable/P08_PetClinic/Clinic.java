package L03_IterableAndComparable.P08_PetClinic;

public interface Clinic<Room> extends Iterable<Room> {
    boolean addPet(Pet pet);

    boolean releasePet();

    L03_IterableAndComparable.P08_PetClinic.Room getRoom(int index);

    boolean hasEmptyRooms();
}
