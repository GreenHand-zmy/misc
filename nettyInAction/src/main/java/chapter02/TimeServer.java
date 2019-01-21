package chapter02;

public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "Nio-Server-001").start();
    }
}
