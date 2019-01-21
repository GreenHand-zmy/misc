package ReadWriteLock;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(10);
        new ReaderThread("reader1", data).start();
        new ReaderThread("reader2", data).start();
        new ReaderThread("reader3", data).start();
        new ReaderThread("reader4", data).start();
        new ReaderThread("reader5", data).start();
        new WriterThread("writer1", data, "张明远").start();
        new WriterThread("writer2", data, "叶振东").start();
    }
}
