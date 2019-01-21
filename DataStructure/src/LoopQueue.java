public class LoopQueue<E> {
    private E[] elements;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        // 循环队列浪费一个元素空间
        elements = (E[]) new Object[capacity + 1];

        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public boolean isEmpty() {
        return front == tail;
    }

    private boolean isFull() {
        return (tail + 1) % elements.length == front;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(E e) {
        // 队列已满,进行扩容
        if (isFull()) {
            grow(size + 1);
        }

        elements[tail] = e;
        tail = (tail + 1) % elements.length;
        size++;
    }

    private void grow(int needed) {
        // 先直接扩容1.5倍
        E[] newElements = (E[]) new Object[elements.length + elements.length >> 1];

        // 数据复制
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        elements = newElements;
        front = 0;
        tail = size;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        // 出队操作
        E element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        return element;
    }
}
