package temp.queue;

public class ServerThread extends Thread {
    private final String threadName;
    private final RequestQueue queue;

    public ServerThread(String threadName, RequestQueue queue) {
        this.threadName = threadName;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Request request = queue.getRequest();
            System.out.println(threadName + " 处理了[" + request + "]");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
