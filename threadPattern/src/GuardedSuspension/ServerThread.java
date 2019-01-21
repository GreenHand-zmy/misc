package GuardedSuspension;

import java.util.Random;

public class ServerThread extends Thread {
    private final RequestQueue queue;

    public ServerThread(String name, RequestQueue queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            Request request = queue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
