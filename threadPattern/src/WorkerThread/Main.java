package WorkerThread;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();
        new ClientThread("张明远", channel).start();
        new ClientThread("叶振东", channel).start();
    }
}
