package chapter1.content;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 环形链表构造队列
 * 循环遍历注意会多次遍历。
 */
public class CircleQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    public boolean isEmpty() {
        return N == 0;
    }

    public int length() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = first;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Node oldFirst = first;
        first = oldFirst.next;
        last.next = first;
        N--;
        return oldFirst.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    private class QueueIterator implements Iterator<Item> {
        int times = 0;
        Node current = first;
        int length = length();

        @Override
        public boolean hasNext() {
            return times < length;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            times++;
            return item;
        }
    }

    public static void main(String[] args) {
        CircleQueue<Integer> circleQueue = new CircleQueue<>();
        circleQueue.enqueue(1);
        circleQueue.enqueue(2);
        for (Integer integer : circleQueue) {
            System.out.println(integer);
        }
    }
}
