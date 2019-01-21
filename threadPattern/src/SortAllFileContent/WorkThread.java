package SortAllFileContent;

public class WorkThread extends Thread {
    private final Channel channel;

    public WorkThread(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            Request request = channel.getRequest();
            request.execute();
        }
    }
}
