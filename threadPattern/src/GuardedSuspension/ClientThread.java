package GuardedSuspension;

import java.util.Random;

public class ClientThread extends Thread {
    private final RequestQueue queue;

    public ClientThread(String name, RequestQueue queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        // 客户端线程一直发送请求
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No." + i);
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            queue.putRequest(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
