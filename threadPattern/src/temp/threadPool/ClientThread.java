package temp.threadPool;

import temp.RandomString;

import java.util.concurrent.atomic.AtomicInteger;

public class ClientThread extends Thread {
    private final Channel channel;
    private AtomicInteger id = new AtomicInteger(0);
    private volatile boolean terminated = false;

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            while (!terminated) {
                Request request = new Request(id.incrementAndGet(), RandomString.makeRandomString(5));
                channel.putRequest(request);
            }
        } catch (InterruptedException e) {
            terminated = true;
        }

    }

    public void stopThread() {
        terminated = true;
        interrupt();
    }
}
