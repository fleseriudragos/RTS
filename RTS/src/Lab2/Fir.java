package Lab2;

import java.util.Observable;

public class Fir extends Observable implements Runnable {

	int id;
	int cc;
	Thread t;
	Window win;
	int processorLoad;

	Fir(int id, Window win, int procLoad, int priority) {

		this.id = id;
		this.win = win;
		this.t = new Thread(this, "Fir");
		this.processorLoad = procLoad;
		this.t.setPriority(priority);

	}

	public int get_cc() {
		return this.cc;
	}

	public int get_id() {
		return this.id;
	}

	public void run() {

		int c = 0;
		while (c < 1000) {
			for (int j = 0; j < this.processorLoad; j++) {
				j++;
				j--;

			}
			c++;
			cc = c;
			
		}
		this.setChanged();
		this.notifyObservers();

	}
}
