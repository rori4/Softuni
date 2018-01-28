package L03_IterableAndComparable.P04_Froggy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Lake<T extends Integer> implements Iterable<Integer> {
    private List<Integer> steps;

    public Lake(Integer... steps) {
        this.steps = Arrays.asList(steps);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }

    private final class Frog implements Iterator<Integer>{
        private int index;
        private List<Integer> indexes;

        public Frog() {
            this.index = 0;
            setIndexes();
        }

        @Override
        public boolean hasNext() {
            return this.index < steps.size();
        }

        @Override
        public Integer next() {
            return steps.get(this.indexes.get(this.index++));
        }

        private void setIndexes() {
            this.indexes = new ArrayList<>();

            for (int i = 0; i < steps.size(); i+=2) {
                this.indexes.add(i);
            }

            for (int i = 1; i < steps.size(); i+=2) {
                this.indexes.add(i);
                            }
        }
    }
}
