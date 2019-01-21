package SortAllFileContent;

public class Channel {
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int tail = 0;
    private int head = 0;
    private int count = 0;
    private WorkThread[] WorkThreadPool;

    public Channel(int threads) {
        // 设置请求队列最大数量
        requestQueue = new Request[MAX_REQUEST];
        // 初始化线程池
        WorkThreadPool = new WorkThread[threads];
        for (int i = 0; i < threads; i++) {
            WorkThreadPool[i] = new WorkThread(this);
        }
    }

    public synchronized void putRequest(Request request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }

    public synchronized Request getRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }

    public void startWorkers() {
        for (int i = 0; i < WorkThreadPool.length; i++) {
            WorkThreadPool[i].start();
        }
    }
}
