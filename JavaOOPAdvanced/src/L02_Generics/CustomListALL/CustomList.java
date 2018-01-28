package L02_Generics.CustomListALL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomList<T extends Comparable> implements Iterable<T> {
    private List<T> myList;

    public CustomList() {
        this.myList = new ArrayList<T>();
    }

    public List<T> getMyList() {
        return this.myList;
    }

    void add(T element){
        this.myList.add(element);
    }
    T remove(int index){
        return this.myList.remove(index);
    }
    boolean contains(T element){
        return this.myList.contains(element);
    }
    void swap(int index1, int index2){
        T element = myList.get(index1);
        myList.set(index1,myList.get(index2));
        myList.set(index2,element);
    }
    int countGreaterThan(T element){
        int counter = 0;
        for (T t : myList) {
            if (element.compareTo(t) < 0){
                counter++;
            }
        }
        return counter;
    }

    T getMax(){
        T element = myList.get(0);
        for (T t : myList) {
            if (element.compareTo(t) < 0){
                element = t;
            }
        }
        return element;
    }
    T getMin(){
        T element = myList.get(0);
        for (T t : myList) {
            if (element.compareTo(t) > 0){
                element = t;
            }
        }
        return element;
    }

    public int getSize() {
        return this.myList.size();
    }

    public T getElement(int index) {
        return this.myList.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return this.myList.iterator();
    }
}
