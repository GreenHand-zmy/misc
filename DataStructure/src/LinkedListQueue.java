public class LinkedListQueue<E> {
    private class Node {
        E data;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E data) {
        if (tail == null) {
            tail = new Node(data);
            head = tail;
        } else {
            tail.next = new Node(data);
            tail = tail.next;
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Cannot dequeue form an empty queue.");
        }
        Node retNode = head;
        retNode.next = null;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return retNode.data;
    }
}
