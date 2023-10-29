package edu.hw3.Task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class BackwardIterator<T> implements Iterator<T> {

    private final ListIterator<T> iterator;

    public BackwardIterator(Collection<T> collection) {
        ArrayList<T> arrayList = new ArrayList<>(collection);
        this.iterator = arrayList.listIterator(arrayList.size());
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public T next() {
        return iterator.previous();
    }

}
