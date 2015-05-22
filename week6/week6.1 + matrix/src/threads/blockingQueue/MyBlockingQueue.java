package threads.blockingQueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyBlockingQueue<T> {

    public List<T> queue = new LinkedList<>();
    private int capacity = 50;

    public MyBlockingQueue(int limit) {
        capacity = limit;
    }

    public synchronized boolean offer(T e) {

        while (queue.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

                return false;
            }
        }

        if (queue.isEmpty()) {
            System.out.println("notify send to poll");
            notifyAll();
        }

        queue.add(e);
        System.out.println(queue);

        return true;
    }

    public synchronized T poll() {
        T o = null;
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (queue.size() == capacity) {
            System.out.println("notify send to offer");
            notifyAll();
        }
        o = queue.remove(0);
        System.out.println(queue);

        return o;

    }
}
