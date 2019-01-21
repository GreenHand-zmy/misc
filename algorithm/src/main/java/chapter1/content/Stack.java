package chapter1.content;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 栈(ADT)
 * Created by ZMY on 2017/7/16.
 */
public class Stack<Item> implements Iterable<Item> {
    // 栈顶
    private Node first;
    // 元素数量
    private int N;

    // 链式结构
    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        // 向栈顶添加元素
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack underflow");
        }
        // 弹出栈顶元素
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Item peek() {
        // 返回最近加入的元素而不是弹出
        if (isEmpty()) {
            throw new NoSuchElementException("stack underflow");
        }
        return first.item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

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
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
    }

}
