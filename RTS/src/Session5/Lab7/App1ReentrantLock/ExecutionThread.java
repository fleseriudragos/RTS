package Session5.Lab7.App1ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {
    int[] activity1, activity2;
    int sleep;
    ReentrantLock lock1, lock2;
    CyclicBarrier barrier;

    public ExecutionThread(int sleep, int[] activity1, int[] activity2, ReentrantLock lock1, ReentrantLock lock2, CyclicBarrier barrier) {

        this.sleep = sleep;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.barrier = barrier;
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
                lock1.lock();
                try {
                    System.out.println(this.getName() + " - STATE 2");
                    k = (int) (Math.random() * (activity2[1] - activity2[0]) + activity2[0]);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    System.out.println(this.getName() + " - TRANSITION 2 - 3");
                    lock2.lock();
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                    } finally {
                        lock2.unlock();
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock1.unlock();
                }

                System.out.println(this.getName() + " - TRANSITION 3 - 4");
                System.out.println(this.getName() + " - STATE 4");

                try {
                    barrier.await(); // Wait for all threads to reach STATE 4
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (this.getName().equals("Thread-1")) {
                lock2.lock();
                try {
                    System.out.println(this.getName() + " - STATE 2");
                    k = (int) (Math.random() * (activity2[1] - activity2[0]) + activity2[0]);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    System.out.println(this.getName() + " - TRANSITION 2 - 3");
                    lock1.lock();
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                    } finally {
                        lock1.unlock();
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock2.unlock();
                }

                System.out.println(this.getName() + " - TRANSITION 3 - 4");
                System.out.println(this.getName() + " - STATE 4");

                try {
                    barrier.await(); // Wait for all threads to reach STATE 4
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
