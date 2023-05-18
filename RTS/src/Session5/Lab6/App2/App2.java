package Session5.Lab6.App2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class App2 {

    private static final int NUM_THREADS = 3;
    private static final int ITERATIONS = 100;
    private static final int MIN_VALUE = -10;
    private static final int MAX_VALUE = 10;
    private static final String FILE_NAME = "results.txt";

    private static int[] threadIterations = new int[NUM_THREADS];

    private static class Worker implements Runnable {

        private int id;
        private CyclicBarrier barrier;

        public Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            Random rand = new Random();

            for (int i = 1; i <= ITERATIONS; i++) {
                int num = rand.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
                threadIterations[id]++;
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                int sum = Results.getSum();
                sum += num;
                Results.setSum(sum);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Writer implements Runnable {

        @Override
        public void run() {
            try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
                int sum = Results.getSum();
                int iterationNum = Results.getIterationNum();
                writer.write("Iteration " + iterationNum + ": " + sum + "\n");
                if (sum == 0) {
                    for (int i = 0; i < NUM_THREADS; i++) {
                        writer.write("Thread " + i + " iterations: " + threadIterations[i] + "\n");
                    }
                }
                Results.setIterationNum(iterationNum + 1);
                Results.setSum(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Results {

        private static int sum = 0;
        private static int iterationNum = 1;

        public static synchronized int getSum() {
            return sum;
        }

        public static synchronized void setSum(int sum) {
            Results.sum = sum;
        }

        public static synchronized int getIterationNum() {
            return iterationNum;
        }

        public static synchronized void setIterationNum(int iterationNum) {
            Results.iterationNum = iterationNum;
        }
    }
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, new Writer());

        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(new Worker(i, barrier)).start();
        }
    }
}
