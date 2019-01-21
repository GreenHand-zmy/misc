package chapter1.content;

import java.util.NoSuchElementException;

/**
 * 单链表
 * Created by ZMY on 2017/7/18.
 */
public class Linked<Item extends Comparable<Item>> {
    private Node first;
    private int N;


    private class Node {
        Item item;
        Node next;
    }

    /**
     * 单链表长度
     *
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 单链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取独立的结点
     *
     * @param item
     * @return
     */
    public Node createNode(Item item) {
        Node node = new Node();
        node.item = item;
        return node;
    }


    /**
     * 增加节点,尾插法。
     *
     * @param item
     */
    public void add(Item item) {
        Node node = new Node();
        node.item = item;
        // 如单链表为空,直接附给头结点
        if (isEmpty()) {
            first = node;
        } else {
            // 不为空,则插入尾部
            for (Node current = first; current != null; current = current.next) {
                // 遍历节点,判断是否尾结点。
                if (current.next == null) {
                    current.next = node;
                    break;
                }
            }
        }
        // 最后单链表长度加1
        N++;
    }

    /**
     * 删除第k位元素,若元素存在。
     *
     * @param k
     */
    public void delete(int k) {
        Node current = first;
        if (k > length() || k < 1) {
            throw new NoSuchElementException(k + "超出范围");
        }
        // 如果要删除是首结点
        if (k == 1) {
            first = first.next;
        } else {
            for (int i = 0; i < k - 2; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        N--;
    }

    /**
     * 查询单链表是否存在某节点,若存在,返回结点位置。
     *
     * @param node
     * @return
     */
    public int isInLiked(Node node) {
        int i = 1;
        for (Node current = first; current != null; current = current.next) {
            if (current == node) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * 返回第k个结点
     *
     * @param k
     * @return
     */
    public Node getKthNode(int k) {
        if (k > length() || k < 1) {
            throw new NoSuchElementException(k + " 超出范围");
        }
        Node current = first;
        for (int i = 1; i < k; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 删除该节点的后续节点
     *
     * @param node
     */
    public void removeAfter(Node node) {
        int position = isInLiked(node);
        if (position == -1 || position == length()) {
            return;
        }
        delete(position + 1);
    }

    /**
     * 将第二个结点插入链表第一个结点的后续结点
     *
     * @param one
     * @param two
     */
    public void insertAfter(Node one, Node two) {
        int position = isInLiked(one);
        if (position == -1 || two == null) {
            return;
        }
        one.next = two.next;
        one.next = two;
        N++;
    }

    /**
     * 删除所有item为key的结点
     *
     * @param key
     */
    public void remove(Item key) {
        Node current = first;
        while (current != null) {
            if (current.next != null && current.next.item.equals(key)) {
                current.next = current.next.next;
                N--;
            } else {
                current = current.next;
            }
        }
    }

    /**
     * 查找该值是否在单链表中存在
     *
     * @param key
     * @return
     */
    public boolean find(Item key) {
        for (Node current = first; current != null; current = current.next) {
            if (current.item.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回单链表中结点值最大的
     *
     * @return
     */
    public Item max() {
        Item max = first.item;
        for (Node current = first; current != null; current = current.next) {
            if (current.item.compareTo(max) > 0) {
                max = current.item;
            }
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (first == null) {
            result.append("linked is empty");
        } else {
            for (Node current = first; current != null; current = current.next) {
                result.append(current.item + " ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Linked<Integer> linked = new Linked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        int length = linked.length();
        Integer max = linked.max();
        System.out.println(max);
        System.out.println(linked);
    }
}

