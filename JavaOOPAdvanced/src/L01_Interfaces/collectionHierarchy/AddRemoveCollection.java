package L01_Interfaces.collectionHierarchy;

public interface AddRemoveCollection<T> extends AddCollection<T> {
    T remove();
}
