package chapter3.content;

import java.util.NoSuchElementException;

/**
 * 基于数组的有序符号表
 * Created by ZMY on 2017/7/24.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * 动态改变数组大小
     *
     * @param capacity
     */
    private void resize(int capacity) {
        // 构造临时数组
        Key[] tempK = (Key[]) new Comparable[capacity];
        Value[] tempV = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }
        keys = tempK;
        values = tempV;
    }

    /**
     * 基于二分查找
     *
     * @return
     */
    private int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        // 如果该元素不存在,返回该元素应该存放的位置。
        return lo;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        // 如果值为空,删除该键。
        if (value == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        // 查找表是否已存在表中
        if (i < N && key.compareTo(keys[i]) == 0) {
            values[i] = value;
            return;
        }
        // insert new key-value pair
        if (N == keys.length) {
            resize(2 * keys.length);
        }
        // 插入键值对到表中
        for (int j = N; j > i; j--) {
            // 腾出空间给新键值对(往后移动)
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        // 放入新键值对
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            return values[i];
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        int i = rank(key);
        // 键不存在,直接返回。
        if (i == N && key.compareTo(keys[i]) != 0) {
            return;
        }

        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        N--;
        keys[N] = null;
        values[N] = null;
        // resize if 1/4 full
        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty symbol table");
        }
        return keys[N - 1];
    }

    public Key select(int k) {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    /**
     * 向下取整(返回小于该key的最大key)
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        int i = rank(key);
        // 若key存在,直接返回。
        if (i < N && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            // 若key不存在
            return keys[i - 1];
        }
    }

    /**
     * 向上取整(返回大于该key的最小key)
     *
     * @param key
     * @return
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        int i = rank(key);
        if (i == N) {
            return null;
        } else {
            return keys[i];
        }
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<>(10);
        binarySearchST.put("a", 1);
        binarySearchST.put("c", 2);
        binarySearchST.put("b", 2);
    }
}
