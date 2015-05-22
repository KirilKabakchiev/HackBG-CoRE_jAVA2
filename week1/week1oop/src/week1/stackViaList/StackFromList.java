package week1.stackViaList;

import week1.DLList.DLListInterface;
import week1.stack.Stackk;

public class StackFromList implements Stackk {

    private DLListInterface list = FactoryList.factoryMethod();
    private int capacity;

    @Override
    public void push(long j) {
        list.insertFirst((int)j);
    }

    @Override
    public long pop() {
        return list.deleteFirst().iData;
    }

    @Override
    public long peek() {
        long res = 0;
        try {
            res = list.getFirst();
        } catch (NullPointerException ex) {
            System.out.println("Stack is empty!");
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
       return list.size() == capacity;
    }

    @Override
    public int count() {
        return list.size();
    }

}
