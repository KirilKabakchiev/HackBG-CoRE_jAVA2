package threads.blockingQueue;

import java.util.Random;

public class Main {

    public static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        final MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(50);

        Thread a = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(r.nextInt(55));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    blockingQueue.poll();
                }
            }

        });

        Thread b = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(r.nextInt(55));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    blockingQueue.poll();
                }
            }

        });

        Thread c = new Thread(new Runnable() {

            @Override
            public void run() {
                // System.out.println("ASdasd");
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(r.nextInt(55));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    blockingQueue.offer(r.nextInt(20));
                }
            }

        });

        Thread d = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(r.nextInt(55));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    blockingQueue.offer(r.nextInt(20));
                }
            }

        });
        a.start();
        // b.start();
        c.start();
        // d.start();
        a.join();
        // b.join();
        c.join();
        // d.join();

        System.out.println("final" + blockingQueue.queue);
    }

}
