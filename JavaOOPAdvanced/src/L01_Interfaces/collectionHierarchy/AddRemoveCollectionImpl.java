package L01_Interfaces.collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public class AddRemoveCollectionImpl<T> implements AddRemoveCollection<T> {

    private List<T> elements;

    public AddRemoveCollectionImpl() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int add(T element) {
        elements.add(0,element);
        return 0;
    }

    @Override
    public T remove() {
        return this.elements.remove(this.elements.size()-1);
    }
}
