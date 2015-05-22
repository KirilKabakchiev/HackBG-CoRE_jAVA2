package threads.raceCondition;

public class Counter {
    private int counter = 0;

    public synchronized void incrementCounter() {

        counter++;
        System.out.println(Thread.currentThread().getName() + " " + counter);
    }

    public synchronized int getCounter() {
        return counter;
    }
}
