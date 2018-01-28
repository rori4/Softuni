package L03_IterableAndComparable.P08_PetClinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ClinicImpl<T extends Room> implements Clinic<Room>,Iterable<Room> {
    private static final String INVALID_OPERATION_MESSAGE = "Invalid operators!";
    private String name;
    private Room[] rooms;
    private List<Integer> accomodationIdexes;
    private Iterator<Room> releaseIterator;

    public ClinicImpl(String name, int countOfRooms) {
        this.name = name;
        this.setRooms(countOfRooms);
        this.setAccomodationIdexes();
        this.releaseIterator = new ReleaseIterator();
    }

    @Override
    public boolean addPet(Pet pet){
        for (Integer accomodationIndex : accomodationIdexes) {
            if(this.rooms[accomodationIndex] == null){
                this.rooms[accomodationIndex] = new RoomImpl(pet);
                if (this.rooms != null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean releasePet(){
        while (this.releaseIterator.hasNext()) {
            if (this.releaseIterator.next() != null) {
                this.releaseIterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Room getRoom(int index){
        return this.rooms[index-1];
    }

    @Override
    public Iterator<Room> iterator() {
        return new ClinicInterator();
    }

    private void setRooms(int rooms) {
        if (rooms%2==0){
            throw  new IllegalArgumentException(INVALID_OPERATION_MESSAGE);
        }
        this.rooms = new Room[rooms];
    }

    private final class ClinicInterator implements Iterator<Room>{
        private int index;

        public ClinicInterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.index <rooms.length;
        }

        @Override
        public Room next() {
            return rooms[this.index++];
        }

    }

    @Override
    public boolean hasEmptyRooms(){
        return Arrays.asList(this.rooms).contains(null);
    }

    private void setAccomodationIdexes() {
        this.accomodationIdexes = new ArrayList<>();
        int middleIndex = rooms.length/2;
        this.accomodationIdexes.add(middleIndex);
        if (rooms.length == 1){
            return;
        }
        int leftIndex = middleIndex;
        int rightIndex = middleIndex + 1;
        for (int i = 0; i < rooms.length/2; i++) {
            this.accomodationIdexes.add(leftIndex--);
            this.accomodationIdexes.add(rightIndex++);
        }
    }

    private final class ReleaseIterator implements Iterator<Room>{
        private int index;
        private List<Integer> indexes;

        public ReleaseIterator() {
            this.index = 0;
            this.setIndexes();
        }

        @Override
        public boolean hasNext() {
            return this.index < rooms.length;
        }

        @Override
        public void remove(){
            rooms[this.indexes.get(this.index-1)]=null;
        }

        @Override
        public Room next() {
            return rooms[this.index++];
        }

        private void setIndexes(){
            this.indexes = new ArrayList<>();
            int middleElement = rooms.length / 2;
            if (rooms.length == 1){
                return;
            }
            for (int i = middleElement+ 1; i < rooms.length; i++) {
                this.indexes.add(i);
            }
            for (int i = 0; i < middleElement; i++) {
                this.indexes.add(i);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Room room : rooms) {
            sb.append(room==null ?"Room empty" :room).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
