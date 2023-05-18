package Lab2;

public class Main {

	private static final int noOfThreads = 8;

	private static final int processorLoad = 1000000;

	public static void main(String args[]) {

		Window win = new Window(noOfThreads);
		Fir f;

		for (int i = 0; i < noOfThreads; i++) {

			f = new Fir(i, win, processorLoad, i + 2);
			f.addObserver(win);
			f.t.start();

		}

	}

}
