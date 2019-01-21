public class LinkedList<E> {
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
    private int size;

    public LinkedList() {
        head = new Node();
        size = 0;
    }

    private Node add(Node node, E data) {
        if (node == null) {
            return new Node(data);
        }
        node.next = add(node.next, data);
        return node;
    }

    public void add(E data) {
        add(head, data);
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index outBounds");
        }

        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node node = new Node(data);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    public void addFirst(E data) {
        add(0, data);
    }


    public void addLast(E data) {
        add(size, data);
    }

    public E get(int index) {
        Node current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E data) {
        Node current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }

    public boolean contains(E data) {
        Node current = head.next;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E remove(int index) {
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size--;
        return delNode.data;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        Node current = head.next;
        while (current != null) {
            result.append(current)
                    .append("->");
            current = current.next;
        }
        result.append("NULL");

        return result.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        System.out.println(list);
    }
}
