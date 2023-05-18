package Session5.Lab7.App3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Integer monitor1 = 1;
        Integer monitor2 = 2;


        CountDownLatch latch = new CountDownLatch(3);

        ExecutionThread thread1 = new ExecutionThread(monitor1, monitor2, 7, 2, 3, latch);
        ExecutionThreadFinal thread2 = new ExecutionThreadFinal(monitor1, 5, 3, 5, thread1, latch);
        ExecutionThreadFinal thread3 = new ExecutionThreadFinal(monitor2, 5, 4, 6, thread2, latch);

        thread1.start();
        thread2.start();
        thread3.start();

        latch.await(); // Wait for all threads to complete

        // Final synchronization
        System.out.println("All threads have completed their execution.");
    }
}