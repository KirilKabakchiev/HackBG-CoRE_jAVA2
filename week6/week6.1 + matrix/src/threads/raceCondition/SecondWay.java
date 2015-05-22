package threads.raceCondition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import sun.awt.Mutex;

public class SecondWay {

     public static AtomicInteger counter2 = new AtomicInteger(0);
    public static int counter = 0;
    // public static Mutex mutex = new Mutex();
    public static Lock lock = new ReentrantLock();

    public static synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
      //  System.out.println(Thread.currentThread().getName());

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println(Thread.currentThread().getName());

                for (int i = 0; i < 200000; ++i) {
                    // mutex.lock();
                    lock.lock();
                 //   counter2.incrementAndGet();
                   
                    counter++;
                    // increment();

                    // mutex.unlock();
                   lock.unlock();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 200000; ++i) {
                    // mutex.lock();
                    lock.lock();

                    
                   // increment();
                    counter++;
                    //counter2.incrementAndGet();
                    // mutex.unlock();
                    lock.unlock();
                }
            }
        });

        long startTime = System.currentTimeMillis();

        a.start();
        b.start();

        a.join();
        b.join();

        System.out.println("counter = " + counter);
        System.out.println("counter2 = " + counter2);
        System.out.println("Duration: " + (System.currentTimeMillis() - startTime));
    }

}