package Session5.Lab7.App3;

import java.util.concurrent.CountDownLatch;

public class ExecutionThreadFinal extends Thread {
    private Integer monitor;
    private Thread t;
    private int sleep, activity_min, activity_max;
    private CountDownLatch latch;

    public ExecutionThreadFinal(Integer monitor, int sleep, int activity_min, int activity_max, Thread t, CountDownLatch latch) {
        this.monitor = monitor;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.t = t;
        this.latch = latch;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        System.out.println(this.getName() + " - STATE 3");
        if (t != null) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }
}
