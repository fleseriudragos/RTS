package Lab5.App4;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {

    int[] activity1, activity2;
    int sleep;
    ReentrantLock lock1, lock2;

    public ExecutionThread( int sleep, int[] activity1, int[] activity2, ReentrantLock lock1, ReentrantLock lock2) {

        this.sleep = sleep;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.lock1 = lock1;
        this.lock2 = lock2;

    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity1[1] - activity1[0]) + activity1[1]);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        System.out.println(this.getName() + " - TRANSITION 1 - 2");
        if (this.lock1.tryLock()) {

            try {
                System.out.println(this.getName() + " - STATE 2");
                k = (int) Math.round(Math.random() * (activity2[1] - activity2[0]) + activity2[1]);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                System.out.println(this.getName() + " - TRANSITION 2 - 3");

                if ( this.lock2.tryLock()) {
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                    } finally {
                        lock2.unlock();
                    }
                }
            } finally {
                lock1.unlock();
            }
            System.out.println(this.getName() + " - TRANSITION 3 - 4");
            try {
                Thread.sleep(sleep);
            } catch (Exception e) {

            }

        }

        if (this.lock2.tryLock()) {
            lock2.lock();
            try {
                System.out.println(this.getName() + " - STATE 2");
                k = (int) Math.round(Math.random() * (activity2[1] - activity2[0]) + activity2[1]);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                System.out.println(this.getName() + " - TRANSITION 2 - 3");
                if (this.lock1.tryLock()) {
                    try {
                        System.out.println(this.getName() + " - STATE 3");
                    } finally {
                        lock1.unlock();
                    }
                }
            } finally {
                lock2.unlock();
            }
            System.out.println(this.getName() + " - TRANSITION 3 - 4");
            try {
                Thread.sleep(sleep);
            } catch (Exception e) {

            }

        }
        System.out.println(this.getName() + " - STATE 4");
    }

}