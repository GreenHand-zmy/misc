import java.util.Arrays;
import java.util.PrimitiveIterator;

import static java.util.Objects.checkIndex;

public class Array<E> {
    // 默认容量
    private static final int DEFAULT_CAPACITY = 10;

    // 存放数据
    private E[] elementData;

    // 元素个数
    private int size;

    public Array(int capacity) {
        elementData = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return elementData.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity <= 0) {
            return minCapacity;
        }

        return (newCapacity - Integer.MAX_VALUE < 0) ? newCapacity : Integer.MAX_VALUE;
    }

    private void add(E e, Object[] elementData, int s) {
        // 如果数组被塞满,进行扩容
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
        size = s + 1;
    }

    public boolean add(E e) {
        add(e, elementData, size);
        return true;
    }

    public void add(int index, E e) {
        rangeCheckForAdd(index);

        final int s;
        Object[] elementData;
        // 检查长度
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        // 复制数组
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = e;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("outOfBounds");
        }
    }

    public E remove(int index) {
        checkIndex(index, size);

        E oldValue = elementData[index];

        final Object[] elements = elementData;
        fastRemove(elements, index);

        return oldValue;
    }

    private void fastRemove(Object[] elements, int index) {
        final int newSize;

        // 如果删除的位置不是尾端,则进行复制
        if ((newSize = size - 1) > index) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        // 删除结点
        elements[size = newSize] = null;
    }


    private void checkIndex(int index, int size) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("outOfBounds");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < size; index++) {
            sb.append(elementData[index]).append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Array<Integer> scores = new Array<>(10);
        scores.add(10);
        scores.add(20);
        System.out.println(scores);
    }
}
