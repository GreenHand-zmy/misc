package WorkerThread;

public class WorkThread extends Thread {
    private final Channel channel;

    public WorkThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = channel.getRequest();
                request.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
