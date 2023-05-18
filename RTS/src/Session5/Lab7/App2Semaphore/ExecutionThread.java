package Session5.Lab7.App2Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;

public class ExecutionThread extends Thread {

    int activitymin, activitymax;
    int sleep;
    Semaphore semaphore1, semaphore2;
    CountDownLatch latch;
    int permit;

    public ExecutionThread(Semaphore semaphore1, Semaphore semaphore2, int activitymax, int activitymin, int sleep, int permit, CountDownLatch latch) {
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.activitymax = activitymax;
        this.activitymin = activitymin;
        this.sleep = sleep;
        this.permit = permit;
        this.latch = latch;
    }

    public void run() {
        while (true) {
            if (this.getName().equals("Thread-0")) {
                try {
                    System.out.println(this.getName() + " - STATE 1");
                    this.semaphore1.acquire(this.permit);
                    System.out.println("Fir " + this.getName() + " took a token from the semaphore");

                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    this.semaphore1.release();
                    System.out.println("Fir " + this.getName() + " released a token from the semaphore");
                    try {
                        Thread.sleep(sleep * 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + " - STATE 3");
                    latch.countDown();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if (this.getName().equals("Thread-1")) {
                try {
                    System.out.println(this.getName() + " - STATE 1");
                    this.semaphore1.acquire(this.permit);
                    this.semaphore2.acquire(this.permit);
                    System.out.println("Fir " + this.getName() + " took a token from the semaphore 1");
                    System.out.println("Fir " + this.getName() + " took a token from the semaphore 2");

                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    this.semaphore1.release();
                    this.semaphore2.release();
                    System.out.println("Fir " + this.getName() + " released a token from the semaphore 1");
                    System.out.println("Fir " + this.getName() + " released a token from the semaphore 2");
                    try {
                        Thread.sleep(sleep * 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + " - STATE 3");
                    latch.countDown();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if (this.getName().equals("Thread-2")) {
                try {
                    System.out.println(this.getName() + " - STATE 1");
                    this.semaphore2.acquire(this.permit);
                    System.out.println("Fir " + this.getName() + " took a token from the semaphore");

                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    this.semaphore2.release();
                    System.out.println("Fir " + this.getName() + " released a token from the semaphore");
                    try {
                        Thread.sleep(sleep * 500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(this.getName() + " - STATE 3");
                    latch.countDown();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}