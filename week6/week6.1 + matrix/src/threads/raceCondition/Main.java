package threads.raceCondition;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        IncrementThread pt1 = new IncrementThread("A", counter);
        IncrementThread pt2 = new IncrementThread("B", counter);
        IncrementThread pt3 = new IncrementThread("C", counter);
        IncrementThread pt4 = new IncrementThread("D", counter);
        Thread t4 = new Thread(pt4);
        Thread t3 = new Thread(pt3);
        Thread t2 = new Thread(pt2);
        Thread t1 = new Thread(pt1);
        long startTime = System.currentTimeMillis();

        t1.start();

        t2.start();

        t3.start();

        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        // Thread.currentThread().join(100000);
        System.out.println("Duration: " + (System.currentTimeMillis() - startTime));
        System.out.println("MAIN" + counter.getCounter());
    }
}
