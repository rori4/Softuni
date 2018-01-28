package L02_Generics.CustomListALL;

public class Sorter {
    public static <T extends Comparable> void sort(CustomList<T> list){
        int n = list.getSize();
        boolean isSwapped;

        do{
            isSwapped = false;
            for (int i = 1; i < n; i++) {
                if (list.getElement(i-1).compareTo(list.getElement(i)) > 0){
                    list.swap(i-1,i);
                    isSwapped = true;
                }
            }
        } while (isSwapped);
    }
}
