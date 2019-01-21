package ReadWriteLock;

public class ReaderThread extends Thread {
    private final Data data;

    public ReaderThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(read));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
