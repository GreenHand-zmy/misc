package ReadWriteLock;

public class Data {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private char[] doRead() {
        char[] newbuf = new char[buffer.length];
        for (int i = 0; i < newbuf.length; i++) {
            newbuf[i] = buffer[i];
        }
        return newbuf;
    }

    private void doWrite(char c) {
        for (int i =0;i<buffer.length;i++){
            buffer[i] = c;
        }
    }
}
