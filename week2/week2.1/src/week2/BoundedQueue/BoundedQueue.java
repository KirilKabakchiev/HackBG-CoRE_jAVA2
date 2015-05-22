package week2.BoundedQueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BoundedQueue<T> implements Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private LinkedList<T> list;
    private int limit;

    public BoundedQueue() {
        limit = DEFAULT_CAPACITY;
    }

    public BoundedQueue(int limit) {
        setLimit(limit);
        list = new LinkedList<>();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 0) {
            this.limit = limit;
        } else {
            throw new IllegalArgumentException("NEgative limit input");
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        try {
            T removeFirst = list.removeFirst();
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
       return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (limit == 0) {
            return false;
        }
        for (T element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean add(T e) {
        if (limit == 0) {
            return false;
        }
        if (list.size() == limit) {
            list.removeFirst();
        }
        list.add(e);
        return true;
    }

    @Override
    public boolean offer(T e) {
        add(e);
        return true;
    }

    @Override
    public T remove() {
        return list.remove();
    }

    @Override
    public T poll() {
        return list.poll();
    }

    @Override
    public T element() {
        return list.element();
    }

    @Override
    public T peek() {
        return list.peek();
    }

}
