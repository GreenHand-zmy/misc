package temp.queue;

import java.util.LinkedList;

public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public synchronized void putRequest(Request request) {
        // 加入队首
        queue.offer(request);
        // 通知所有在该实例等待的线程
        System.out.println(Thread.currentThread().getName() + " 存放了一个请求");
        notifyAll();
    }

    public synchronized Request getRequest() {
        // 队列为空则等待
        while (queue.peek() == null) {
            try {
                System.out.println(Thread.currentThread().getName() + "无请求 等待请求中....");
                wait();
                System.out.println(Thread.currentThread().getName() + "无请求 等待结束了....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }
}
