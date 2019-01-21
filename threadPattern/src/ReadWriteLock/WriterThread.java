package ReadWriteLock;

public class WriterThread extends Thread {
    private final Data data;
    // 从filter中挨个选取一个字符插入
    private final String filter;
    private int index = 0;

    public WriterThread(String name, Data data, String filter) {
        super(name);
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                char c = nextChar();
                data.write(c);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private char nextChar() {
        char c = filter.charAt(index);
        index++;
        if (index >= filter.length()) {
            index = 0;
        }
        return c;
    }
}
