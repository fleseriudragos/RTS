package Session5.Lab7.App1Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Integer[] monitor = {1, 2};
        int[] activity1 = {1, 2};
        int[] activity2 = {3, 4};
        int sleep = 1000;

        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        CountDownLatch latch = new CountDownLatch(2); // Initialize CountDownLatch with the number of threads

        ExecutionThread thread1 = new ExecutionThread( sleep, activity1, activity2, semaphore1, semaphore2, latch);
        ExecutionThread thread2 = new ExecutionThread( sleep, activity1, activity2, semaphore1, semaphore2, latch);

        thread1.start();
        thread2.start();

        try {
            latch.await(); // Wait for both threads to reach STATE 4
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads reached STATE 4.");

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads joined.");
    }
}

