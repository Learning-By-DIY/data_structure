package com.github;

/**
 * @author guwanhua
 * @create 2020-05-23 14:08
 */
public class Array<E> {
    private E[] data;
    // 数组真实存储数据的长度, 同时指向下一个要填充的位置
    private int size;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组元素个数
    public int getSize() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 扩充数组容量为newCapacity大小
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 在index位置插入一个元素
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        if (size == data.length)
            resize(2 * size);
        for (int i = size - 1; i >= index; i--)
            data[i+1] = data[i];
        data[index] = e;
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++){
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for (int i = index; i < size; i++)
            data[i] = data[i+1];

        size--;
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
