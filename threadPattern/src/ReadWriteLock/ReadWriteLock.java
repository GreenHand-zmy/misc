package ReadWriteLock;

/**
 * 读写锁
 * Created by zmy on 2018/1/8.
 */
public class ReadWriteLock {
    // 实际正在读取中的线程个数
    private int readingReaders = 0;
    // 正在等待写入的线程个数
    private int waitingWriters = 0;
    // 实际正在写入的线程个数
    private int writingWriters = 0;
    // 写入优先
    private boolean preferWriter = true;

    //执行读取加锁
    public synchronized void readLock() throws InterruptedException {
        // 如有线程在写入,则一直等待,或写入优先并且有待写入的线程
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        // 实际读取的线程数量 +1
        readingReaders++;
    }

    // 执行读取解锁
    public synchronized void readUnlock() {
        readingReaders--;
        preferWriter = true;
        notifyAll();
    }

    // 执行写入加锁
    public synchronized void writeLock() throws InterruptedException {
        // 等待写入的线程数 +1
        waitingWriters++;
        try {
            // 如果当前有读取的线程或者写入的线程则一直等待
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            // 等待写入的线程数 -1
            waitingWriters--;
        }
        // 正在执行写入的线程 +1
        writingWriters++;
    }

    // 执行写入解锁
    public synchronized void writeUnlock() {
        writingWriters--;
        preferWriter = false;
        notifyAll();
    }
}
