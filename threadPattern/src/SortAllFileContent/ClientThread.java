package SortAllFileContent;


public class ClientThread extends Thread {
    private final Channel channel;

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        // 发送10次请求
        for (int i = 0; i < 10; i++) {
            channel.putRequest(new WriteRequest());
        }
    }
}
