package Lab5.App1;

import java.util.concurrent.locks.Lock;

class Fir extends Thread {
    int name;
    Lock l;

    Fir(int n, Lock la) {
        this.name = n;
        this.l = la;
    }

    public void run() {
        this.l.lock();
        System.out.println("The thread " + name + " acquired the lock");
        criticalRegion();
        this.l.unlock();
        System.out.println("The thread " + name + " released the lock");
    }

    public void criticalRegion() {
        System.out.println("IS EXECUTING THE CRITICAL REGION!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}