package com.github;

/**
 * @author guwanhua
 * @create 2020-05-23 16:20
 */
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
