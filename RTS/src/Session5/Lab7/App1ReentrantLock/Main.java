package Session5.Lab7.App1ReentrantLock;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Integer[] monitor = { 0, 1};

        int[] activity1 = { 2, 4 };
        int[] activity2 = { 4, 6 };
        int[] activity3 = { 3, 5 };
        int[] activity4 = { 5, 7 };
        ReentrantLock lock1=new ReentrantLock();
        ReentrantLock lock2= new ReentrantLock();
        CyclicBarrier barrier=new CyclicBarrier(2);

        ExecutionThread thread1 = new ExecutionThread( 4, activity1, activity2,lock1,lock2,barrier);
        ExecutionThread thread2= new ExecutionThread( 5, activity3, activity4,lock1,lock2,barrier);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
