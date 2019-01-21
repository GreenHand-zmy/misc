package GuardedSuspension;

import java.util.LinkedList;
import java.util.Queue;

// 用来存放请求的队列
public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<>();

    // 获取请求
    public synchronized Request getRequest() {
        // 如果队列暂时为空,让线程一直等待直到队列不为空
        while (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    // 放入请求
    public synchronized void putRequest(Request request) {
        // 将请求放入队尾
        queue.offer(request);
        //通知所有等待的线程
        notifyAll();
    }
}
