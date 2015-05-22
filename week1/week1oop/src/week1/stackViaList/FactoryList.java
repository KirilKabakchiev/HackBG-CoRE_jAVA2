package week1.stackViaList;

import week1.DLList.*;

public class FactoryList {

    public static DLListInterface factoryMethod() {
        return new DLListInterface() {
            private Link first = null;
            private Link last = null;
            private int size = 0;

            private boolean containsElement(int key) {
                Link current = first;
                while (current.iData != key) {
                    current = current.next;
                    if (current == null)
                        return false;
                }
                return true;
            }

            public boolean isEmpty() {
                return first == null;
            }
            
            public void insertFirst(int dd) {
                if (containsElement(dd)) {
                    return;
                }
                Link newLink = new Link(dd);
                if (isEmpty()) {
                    last = newLink;
                } else {
                    first.previous = newLink;
                }
                newLink.next = first;
                first = newLink;
                size++;
            }

            public void insertLast(int dd) {
                if (containsElement(dd)) {
                    return;
                }
                Link newLink = new Link(dd);
                if (isEmpty()) {
                    first = newLink;
                } else {
                    last.next = newLink;
                    newLink.previous = last;
                }
                last = newLink;
                size++;
            }

            public Link deleteFirst() {
                Link temp = first;
                if (first.next == null) {
                    last = null;
                } else {
                    first.next.previous = null;
                }
                first = first.next;
                size--;
                return temp;
            }

            public Link deleteLast() {
                Link temp = last;
                if (first.next == null) {
                    first = null;
                } else {
                    last.previous.next = null;
                }
                last = last.previous;
                size--;
                return temp;
            }

            public int getFirst() throws NullPointerException {
                if (!isEmpty()) {
                    return first.iData;
                } else {
                    throw new NullPointerException();
                }
            }

            public int getLast() throws NullPointerException {
                if (!isEmpty()) {
                    return last.iData;
                } else {
                    throw new NullPointerException();
                }
            }

            public boolean insertAfter(int key, int dd) {
                if (containsElement(dd)) {
                    return false;
                }
                Link current = first;
                while (current.iData != key) {
                    current = current.next;
                    if (current == null) {
                        return false;
                    }
                }
                Link newLink = new Link(dd);

                if (current == last) {
                    newLink.next = null;
                    last = newLink;
                } else {
                    newLink.next = current.next;

                    current.next.previous = newLink;
                }
                newLink.previous = current;
                current.next = newLink;
                size++;
                return true;
            }

            public Link deleteKey(int key) {
                Link current = first;
                while (current.iData != key) {
                    current = current.next;
                    if (current == null)
                        return null;
                }
                if (current == first) {
                    first = current.next;
                } else {
                    current.previous.next = current.next;
                }

                if (current == last) {
                    last = current.previous;
                } else {
                    current.next.previous = current.previous;
                }
                size--;
                return current;
            }

//            public int getByKey(int key) {
//                Link current = first;
//                while (current.iData != key) {
//                    current = current.next;
//                    if (current == null)
//                        return 0;
//                }
//                return current.iData;
//            }

            public int getByIndex(int index) throws IndexOutOfBoundsException {
                if (index >= size) {
                    throw new IndexOutOfBoundsException("invalid index");
                }
                int i = 0;
                Link current = first;
                while (i < index) {
                    current = current.next;
                    i++;
                }
                return current.iData;
            }

            public String toString() {
                String str = "List (first-->last): ";
                Link current = first;
                while (current != null) {
                    str += current.toString();
                    current = current.next;
                }
                System.out.println("");
                System.out.print("List (last-->first): ");

                current = last;
                while (current != null) {
                    str += current.toString();
                    current = current.previous;
                }
                return str;
            }

            public int size() {
                return size;
            }
        };
    }
}
