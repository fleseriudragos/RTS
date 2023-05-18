package Lab3.App1;

import Lab3.App1.FileService;
public class WThread extends Thread {
    FileService service;

    public WThread(FileService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            synchronized (service) { // sincronizeaza pe clasa Main
                String msg =
                        String.valueOf(Math.round(Math.random() * 100));
                service.write(msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}