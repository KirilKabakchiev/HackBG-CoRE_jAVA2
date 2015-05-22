package week2.OnOFF;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class OnOffCollection<T> implements Collection<T> {

    private LinkedList<T> list = new LinkedList<>();

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
    public Object[] toArray(Object[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Object e) {
        if (list.contains(e)) {
            list.remove(e);
            return false;
        } else {
            list.add((T) e);
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (list.contains(o)) {
            list.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
       return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection c) {
     return list.addAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {

        return list.removeAll(c);
    }

    @Override
    public void clear() {
        list.clear();

    }

}
