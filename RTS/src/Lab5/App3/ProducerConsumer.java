package Lab5.App3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ProducerConsumer extends Thread{
    ArrayList<Integer> collection;
    String name;
    Lock lock;
    private Random random;
    private Condition condition;


    ProducerConsumer(ArrayList<Integer> collection, Lock lock, String name){
        this.collection = collection;
        this.name = name;
        this.lock = lock;
        this.random = new Random();
        condition = lock.newCondition();
    }

    public void run(){
        while(true){
            try {
                lock.lock();
                while (collection.size() == 100) {
                    try{
                        condition.await();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                int number = random.nextInt(100);
                collection.add(number);
                System.out.println(name + ": " + number);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}



