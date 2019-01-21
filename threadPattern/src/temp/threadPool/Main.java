package temp.threadPool;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        new ClientThread("客户端1",channel).start();
        new ClientThread("客户端2",channel).start();

    }
}
