package chapter1.content;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * exam 1.3.14
 * Created by ZMY on 2017/7/26.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int first;
    private int last;
    private int size;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[2];
        first = 0;
        last = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = size;
    }

    public void enqueue(Item item) {
        if (size == q.length) {
            resize(2 * q.length);
        }
        q[last++] = item;
        if (last == q.length) {
            last = 0;
        }
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = q[first];
        q[first] = null;
        size--;
        first++;
        if (first == q.length) {
            first = 0;
        }
        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item peek() {
        return q[first];
    }

    public static void main(String[] args) {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size + " left on queue)");
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            Item item = q[(first + i) % q.length];
            i++;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
}
