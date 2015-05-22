package ImmutableListImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.apache.commons.lang3.SerializationUtils;


@SuppressWarnings("serial")
public class ImmutableList<T extends Serializable> extends ArrayList<T> {

    public ImmutableList(Collection<? extends T> c) {
        for (T elem : c) {
            T copyElem = (T) SerializationUtils.clone(elem);
            super.add(copyElem);
        }
    }

    @Override
    public void trimToSize() {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public void ensureCapacity(int minCapacity) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override 
    public int size() {
        // TODO Auto-generated method stub
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return super.contains(o);
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        return super.lastIndexOf(o);
    }

    @Override
    public Object clone() {
        return SerializationUtils.clone(this);
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return super.toArray(a);
    }

    @Override
    public T get(int index) {
        return (T) SerializationUtils.clone(super.get(index));
    }

    @Override
    public T set(int index, T element) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public boolean add(T e) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public void add(int index, T element) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public T remove(int index) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public boolean remove(Object o) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public void clear() {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        return super.listIterator(index);
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        return super.listIterator();
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return super.iterator();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return super.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        // TODO Auto-generated method stub
        super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        // TODO Auto-generated method stub
        return super.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    @Override
    public void sort(Comparator<? super T> c) {
        throw new ImmutableListModificationException("the structure is immutable!");
    }

    public static <T extends Serializable> ImmutableList<T> asList(T... args) {
        List<T> list = Arrays.asList(args);
        return new ImmutableList<T>(list);
    }

}
