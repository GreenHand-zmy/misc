package temp.threadPool;

public class Channel {
    private static final int MAX_REQUEST = 100;
    private int count;
    private int head;
    private int tail;
    private final Request[] requestQueue;
    private final WorkThread[] threadPool;


    public Channel(int length) {
        this.requestQueue = new Request[MAX_REQUEST];
        count = 0;
        head = 0;
        tail = 0;
        // 初始化线程池
        threadPool = new WorkThread[length];
        for (int i = 1; i <= threadPool.length; i++) {
            threadPool[i - 1] = new WorkThread(i + " 号工人", this);
        }
        // 启动线程池
        for (int i = 1; i <= threadPool.length; i++) {
            threadPool[i - 1].start();
        }
    }

    public synchronized void putRequest(Request request) throws InterruptedException {
        // 队列为满,线程阻塞
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

    public synchronized Request takeRequest() throws InterruptedException {
        // 队列为空,线程阻塞
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

    public synchronized void stopAllWorkers() {
        for (WorkThread workThread : threadPool) {
            workThread.stopThread();
        }
    }
}
