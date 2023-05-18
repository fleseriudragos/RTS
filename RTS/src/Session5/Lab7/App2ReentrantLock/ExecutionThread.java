package Session5.Lab7.App2ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {

    int activitymax, activitymin;
    int sleep;
    ReentrantLock lock1, lock2;
    CyclicBarrier barrier;

    public ExecutionThread(int sleep, int activitymax, int activitymin, ReentrantLock lock1, ReentrantLock lock2, CyclicBarrier barrier) {

        this.sleep = sleep;
        this.activitymax = activitymax;
        this.activitymin = activitymin;
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.barrier = barrier;
    }

    public void run() {
        while (true) {
            if (this.getName().equals("Thread-0")) {
                try {
                    System.out.println(this.getName() + " - STATE 1");
                    lock1.lock();
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }

                } finally {
                    lock1.unlock();
                }
                try {
                    Thread.sleep(sleep * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " - STATE 3");
                try {
                    barrier.await(); // Wait for all threads to reach STATE 3
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (this.getName().equals("Thread-1")) {
                try {
                    System.out.println(this.getName() + " - STATE 1");
                    lock1.lock();
                    lock2.lock();
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }

                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
                try {
                    Thread.sleep(sleep * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " - STATE 3");
                try {
                    barrier.await(); // Wait for all threads to reach STATE 3
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.getName().equals("Thread-2")) {
                try {
                    System.out.println(this.getName() + " - STATE 1");
                    lock2.lock();
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }

                } finally {
                    lock2.unlock();
                }
                try {
                    Thread.sleep(sleep * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + " - STATE 3");
                try {
                    barrier.await(); // Wait for all threads to reach STATE 3
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
