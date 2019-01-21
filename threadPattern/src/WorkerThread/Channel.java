package WorkerThread;

// 负责传递工作请求以及保存工人线程的Channel
public class Channel {
    private static final int MAX_REQUEST = 100;
    // 请求队列
    private final Request[] requestQueue;
    // 队列尾
    private int tail;
    // 队列头
    private int head;
    // 队列中元素个数
    private int count;
    private final WorkThread[] threadPool;

    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        threadPool = new WorkThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkThread("worker-" + i, this);
        }
    }

    public void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public synchronized Request getRequest() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        Request request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }

    public synchronized void putRequest(Request request) throws InterruptedException {
        while (count >= requestQueue.length) {
            wait();
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }
}
