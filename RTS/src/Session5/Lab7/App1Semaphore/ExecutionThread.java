package Session5.Lab7.App1Semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
    int[] activity1, activity2;
    int sleep;
    Semaphore semaphore1, semaphore2;
    CountDownLatch latch;

    public ExecutionThread(int sleep, int[] activity1, int[] activity2, Semaphore semaphore1, Semaphore semaphore2, CountDownLatch latch) {

        this.sleep = sleep;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.latch = latch;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            int k = (int) (Math.random() * (activity1[1] - activity1[0]) + activity1[0]);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println(this.getName() + " - TRANSITION 1 - 2");

            if (this.getName().equals("Thread-0")) {
                semaphore1.acquireUninterruptibly();
                try {
                    System.out.println(this.getName() + " - STATE 2");
                    k = (int) (Math.random() * (activity2[1] - activity2[0]) + activity2[0]);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    System.out.println(this.getName() + " - TRANSITION 2 - 3");
                    semaphore2.acquireUninterruptibly();
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                    } finally {
                        semaphore2.release();
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    semaphore1.release();
                }

                System.out.println(this.getName() + " - TRANSITION 3 - 4");
                System.out.println(this.getName() + " - STATE 4");

                latch.countDown(); // Signal that this thread has reached STATE 4
            }

            if (this.getName().equals("Thread-1")) {
                semaphore2.acquireUninterruptibly();
                try {
                    System.out.println(this.getName() + " - STATE 2");
                    k = (int) (Math.random() * (activity2[1] - activity2[0]) + activity2[0]);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    System.out.println(this.getName() + " - TRANSITION 2 - 3");
                    semaphore1.acquireUninterruptibly();
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                    } finally {
                        semaphore1.release();
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    semaphore2.release();
                }

                System.out.println(this.getName() + " - TRANSITION 3 - 4");
                System.out.println(this.getName() + " - STATE 4");

                latch.countDown(); // Signal that this thread has reached STATE 4
            }

        }
    }
}
