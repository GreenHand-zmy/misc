package chapter1.content;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列(ADT)
 * Created by ZMY on 2017/7/16.
 */
public class Queue<Item> implements Iterable<Item> {
    // 队头
    private Node first;
    // 队尾
    private Node last;
    // 队列中元素数量
    private int N;

    // 链表
    private class Node {
        Item item;
        Node next;
    }

    // 入队
    public void enQueue(Item item) {
        // 保存当前队尾元素引用
        Node oldLast = last;
        // 构建新的队尾元素
        last = new Node();
        last.item = item;
        last.next = null;
        // 如果队列为空，队首和队尾指向同一个结点
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    // 出队
    public Item deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        // 删除头结点
        Item item = first.item;
        first = first.next;
        // 如果队为空，设置队尾为空
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    // 队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 队列元素数量
    public int size() {
        return N;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // 迭代器
    private class ListIterator implements Iterator<Item> {
        // 保存首结点引用
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println(queue);
    }
}
