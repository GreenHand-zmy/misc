package SortAllFileContent;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(3);
        channel.startWorkers();
        new ClientThread("client", channel).start();
    }
}
