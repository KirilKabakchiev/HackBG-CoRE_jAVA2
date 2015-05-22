package week1.DLList;

public interface DLListInterface {

    boolean isEmpty();

    void insertFirst(int dd);

    void insertLast(int dd);

    Link deleteFirst();

    Link deleteLast();

    int getFirst() throws NullPointerException;

    int getLast() throws NullPointerException;

    boolean insertAfter(int key, int dd);

    Link deleteKey(int key);

   // int getByKey(int key);

    int getByIndex(int index) throws IndexOutOfBoundsException;

    String toString();

    int size();
}
