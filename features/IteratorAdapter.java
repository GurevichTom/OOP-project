package features;

import java.util.ListIterator;

/**
 * Adapter design pattern
 * exercise 19
 * @param <T>
 */
public class IteratorAdapter <T> {
    private final ListIterator<T> iterator;

    public IteratorAdapter(ListIterator<T> iterator) {
        this.iterator = iterator;
    }

    public void myAdd(T elem) {
        iterator.add(elem);
    }

    public boolean myHasPrevious() {
        return iterator.hasPrevious();
    }

    public T myPrevious() {
        return iterator.previous();
    }
}
