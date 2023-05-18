package Lab4.App4;

public class Main {
    public static void main(String[] args) {
        Integer monitor1 = 1;
        Integer monitor2 = 2;
        ExecutionThread main = new ExecutionThread(monitor1, monitor2, 7, 7, 2, 3);
        ExecutionThreadFinal ex1 = new ExecutionThreadFinal(monitor1, 0, 0, 3, 5, main);
        ExecutionThreadFinal ex2 = new ExecutionThreadFinal(monitor2, 0, 0, 4, 6, main);
        main.start();
        ex1.start();
        ex2.start();
    }
}
