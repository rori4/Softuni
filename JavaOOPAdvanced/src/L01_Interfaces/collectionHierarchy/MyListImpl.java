package L01_Interfaces.collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public class MyListImpl<T> implements MyList<T> {

    private List<T> elements;

    public MyListImpl() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int add(T element) {
        elements.add(0,element);
        return this.elements.indexOf(element);
    }

    @Override
    public T remove() {
        return this.elements.remove(0);
    }

    @Override
    public int size() {
        return this.elements.size();
    }
}
