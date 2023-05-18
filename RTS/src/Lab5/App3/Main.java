package Lab5.App3;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> collection = new ArrayList<>();
        Lock lock = new ReentrantLock();
        ProducerConsumer producer = new ProducerConsumer(collection, lock, "Producer");
        Consumer consumer1 = new Consumer(collection, lock, "Consumer1");
        Consumer consumer2 = new Consumer(collection, lock, "Consumer2");
        Consumer consumer3 = new Consumer(collection, lock, "Consumer3");

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}
