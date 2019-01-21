package chapter1.content;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.util.Iterator;

/**
 * 定容的栈(数组实现)
 * Created by ZMY on 2017/7/16.
 */
public class FixedCapacityStack<Item> implements Iterable<Item> {
    // 内部存储使用数组
    private Item[] a;
    // 数组长度
    private int size;

    // 构造函数
    public FixedCapacityStack(int size) {
        a = (Item[]) new Object[size];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 返回栈的长度
    public int size() {
        return size;
    }

    // 入栈
    public void push(Item item) {
        // 如果栈空间不够则扩容
        if (size == a.length) {
            resize(2 * a.length);
        }
        a[size++] = item;
    }

    // 出栈
    public Item pop() {
        Item item = a[--size];
        a[size] = null;
        // 判断使用率，如果使用率低于1/4，则缩小容器
        if (size > 0 && size == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    // 改变数组大小
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // 返回一个迭代器
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // 内部类，实现迭代器接口
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }

    public static void main(String[] args) {
        FixedCapacityStack<Integer> fixedCapacityStack = new FixedCapacityStack<>(10);
        fixedCapacityStack.push(1);
        fixedCapacityStack.push(2);
        fixedCapacityStack.push(3);
        fixedCapacityStack.push(4);
        for (Integer pop : fixedCapacityStack) {
            System.out.println(pop);
        }
    }
}
