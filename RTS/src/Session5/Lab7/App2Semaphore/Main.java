package Session5.Lab7.App2Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String args[]) {
        int permit = 1;
        Semaphore semaphore1 = new Semaphore(permit);
        Semaphore semaphore2 = new Semaphore(permit);
        CountDownLatch latch = new CountDownLatch(3);

        ExecutionThread thread1 = new ExecutionThread(semaphore1, semaphore2, 4, 2, 4, permit, latch);
        ExecutionThread thread2 = new ExecutionThread(semaphore1, semaphore2, 6, 3, 3, permit, latch);
        ExecutionThread thread3 = new ExecutionThread(semaphore1, semaphore2, 5, 2, 5, permit, latch);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await(); // Wait for both threads to reach STATE 4
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads reached STATE 3.");

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads joined.");


    }
}