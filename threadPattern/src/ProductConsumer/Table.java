package ProductConsumer;

/**
 * 表示一张桌子，桌子上只能放三个蛋糕
 * Created by zmy on 2018/1/8.
 */
public class Table<Item> {
    private final Item[] buffer;
    // 数组队头
    private int tail;
    // 数组队尾
    private int head;
    // 数组中存在的元素个数
    private int count;

    // 初始化一个桌子只能放置count个元素
    public Table(int count) {
        this.buffer = (Item[]) new Object[count];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    // 放置元素
    public synchronized void put(Item item) throws InterruptedException {
        // 如果当前元素数量大于容量则线程进入等待队列
        while (count >= buffer.length) {
            wait();
        }
        System.out.println(Thread.currentThread().getName() + " puts " + item);
        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;
        notifyAll();
    }

    // 获取元素
    public synchronized Item get() throws InterruptedException {
        while (count <= 0) {
            wait();
        }
        Item item = buffer[head];
        System.out.println(Thread.currentThread().getName() + " gets " + item);
        head = (head + 1) % buffer.length;
        count--;
        notifyAll();
        return item;
    }

}
