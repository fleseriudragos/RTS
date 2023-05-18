package Session5.Lab7.App3;

import java.util.concurrent.CountDownLatch;

public class ExecutionThread extends Thread {
    private Integer monitor1, monitor2;
    private int sleep,activity_min, activity_max;
    private CountDownLatch latch;

    public ExecutionThread(Integer monitor1, Integer monitor2, int sleep, int activity_min, int activity_max, CountDownLatch latch) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.latch = latch;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(Math.round(Math.random() * sleep * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        synchronized (monitor1) {
            synchronized (monitor2) {
                monitor1.notify();
                monitor2.notify();
            }
        }
        System.out.println(this.getName() + " - STATE 3");
        latch.countDown();
    }
}