package temp.queue;

import temp.RandomString;

public class ClientThread extends Thread {
    private final String threadName;
    private final RequestQueue queue;

    public ClientThread(String threadName, RequestQueue queue) {
        this.threadName = threadName;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Request request = new Request(RandomString.makeRandomString(5));
            System.out.println(threadName + " 往服务器提交了[" + request + "]请求");
            queue.putRequest(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
