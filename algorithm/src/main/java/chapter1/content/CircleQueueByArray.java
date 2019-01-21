package chapter1.content;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 循环队列(数组实现,固定长度)
 * Created by ZMY on 2017/7/19.
 */
public class CircleQueueByArray<Item> {
    private int head = 0;
    private int rear = 0;
    private Item[] queueList;

    public CircleQueueByArray(int length) {
        queueList = (Item[]) new Object[length + 1];
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return (head == rear);
    }

    /**
     * 判断队列是否为满
     *
     * @return
     */
    public boolean isFull() {
        return ((rear + 1) % length() == head);
    }

    /**
     * 返回队列长度
     *
     * @return
     */
    public int length() {
        return queueList.length;
    }

    /**
     * 返回队列中有几个元素
     *
     * @return
     */
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear > head ? rear - head : length() - (head - rear);
    }

    /**
     * 入队
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (isFull()) {
            throw new NoSuchElementException("queue is full");
        }
        rear = (rear + 1) % length();
        queueList[rear] = item;
    }

    /**
     * 出队
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        head = (head + 1) % length();
        Item item = queueList[head];
        queueList[head] = null;

        return item;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("i = " + i + " " + "j=" + j);
            }
        }
    }


}
