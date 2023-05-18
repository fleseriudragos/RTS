package Lab5.App5;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String args[]){

        Semaphore semaphore=new Semaphore(2);

        new ExecutionThread(5,semaphore,6,3,1).start();
        new ExecutionThread(3,semaphore,7,4,1).start();
        new ExecutionThread(6,semaphore,7,5,1).start();

    }
}
