package threads.raceCondition;

import java.util.Random;

public class IncrementThread implements Runnable {

    // private static int counter = 0;
    private final String name;
    private Counter counter;
    private Random r = new Random();
    
    public IncrementThread(String _name, Counter c) {
        counter = c;
        name = _name;
    }

    public void run() {
   
        for (int i = 0; i < 20000; i++) {
            
            counter.incrementCounter();
//            try {
//                Thread.sleep(r.nextInt(5));
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
           // System.out.println("Thread " + name + ": " + counter.getCounter());
        }

        // System.out.println("Done incrementing by 1 ..." + counter);
    }

}
