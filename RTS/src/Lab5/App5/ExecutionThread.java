package Lab5.App5;

import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread{

    Semaphore semaphore;
    int activitymax, activitymin;
    int sleep;

    int permit;

    public ExecutionThread(int sleep, Semaphore semaphore, int activitymax, int activitymin,int permit) {
        this.semaphore = semaphore;
        this.activitymax = activitymax;
        this.activitymin = activitymin;
        this.sleep = sleep;
        this.permit=permit;
    }
    public void run()
    {
        while(true)
        {
            try {
                System.out.println(this.getName() + " - STATE 1");
                this.semaphore.acquire(this.permit);
                System.out.println("Fir " + this.getName() + " took a token from the semaphore");

                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.random() * ((activitymax - activitymin) + activitymin);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }

                this.semaphore.release();
                System.out.println("Fir " + this.getName() + " released a token from the semaphore");
                System.out.println(this.getName() + " - STATE 3");

                try {
                    Thread.sleep(sleep*500);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
