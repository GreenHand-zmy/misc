package chapter1.content;

import java.util.Iterator;

/**
 * 背包(ADT)
 * Created by ZMY on 2017/7/16.
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    public Bag() {
        first = null;
        n = 0;
    }

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
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
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        System.out.println(bag);
    }
}
