package Lab3.App1;

public class RThread extends Thread{
    FileService service;
    public RThread(FileService service) {
        this.service = service;
    }

    // doua metode sincronizate pe acelasi obiect => un singur thread poate intra in fiecare metoda;
    public void run(){
        while(true)
        {
            //synchronized blocks = mai multe blocuri sincronizate pe acelasi obiect, fiecare block poate avea un thread in interior
            //synchronized methods = un singur thread poate intra in metoda

            synchronized (service){ // sincronizeaza pe clasa Main

                service.notify(); // notifica un thread care asteapta pe obiectul Main.class
            }
            try {
                System.out.println(service.read());
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
