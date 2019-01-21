package temp.threadPool;

import java.util.Random;

public class WorkThread extends Thread {
    private final Channel channel;
    private volatile boolean terminated = false;

    public WorkThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            while (!terminated) {
                Request request = channel.takeRequest();
                request.execute();
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
