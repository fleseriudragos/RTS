package Lab5.App3;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread{
    private final ArrayList<Integer> collection;
    private final String name;
    private final Lock lock;

    public Consumer(ArrayList<Integer> collection, Lock lock, String name) {
        this.collection = collection;
        this.name = name;
        this.lock = lock;
    }

    public void run(){
        while(true){
            try {
                lock.lock();
                if (!collection.isEmpty()) {
                    int number = extractNumber();
                    displayNumber(number);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private int extractNumber() {
        return collection.remove(0);
    }

    private void displayNumber(int number) {
        System.out.println(name + ": " + number);
    }
}
