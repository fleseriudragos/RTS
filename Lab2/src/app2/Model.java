package app2;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private int[] progressValues;

    public Model(int nrThreads) {
        this.progressValues = new int[nrThreads];
    }

    public synchronized void setProgressValue(int id, int val) {
        progressValues[id] = val;

        setChanged();
        notifyObservers();
    }

    public synchronized int[] getProgressValues() {
        return progressValues;
    }
}
