package temp.queue;

public class Main {
    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        ClientThread client = new ClientThread("谷歌浏览器", queue);
        ServerThread server = new ServerThread("谷歌服务器", queue);
        client.start();
        server.start();
    }
}
