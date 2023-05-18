package Lab2;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Window extends JFrame implements Observer {

    private ArrayList<JProgressBar> bars = new ArrayList<JProgressBar>();

    public Window(int nrThreads) {
        setLayout(null);
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init(nrThreads);
        this.setVisible(true);
    }

    private void init(int n) {
        for (int i = 0; i < n; i++) {
            JProgressBar pb = new JProgressBar();
            pb.setMaximum(1000);
            pb.setBounds(50, (i + 1) * 30, 350, 20);
            this.add(pb);
            this.bars.add(pb);
        }
    }

    public void update(Observable o, Object obj) {
       if(o!=null)
       {
    	   Fir ff=(Fir)o;
    	   int id=ff.get_id();
    	   int cc=ff.get_cc();
    	   bars.get(id).setValue(cc);
       }
    }

   
}