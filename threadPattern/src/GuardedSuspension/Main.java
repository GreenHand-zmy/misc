package GuardedSuspension;

public class Main {
    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        new ClientThread("client", queue).start();
        new ServerThread("server", queue).start();
    }
}
