package project20280.stacksqueues;

import project20280.interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int front = 0;
    private int size = 0;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        // TODO
        data = (E[]) new Object[capacity]; // Object Array created first as generic array creation directly is not allowed in java
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) throws IllegalStateException {
        // TODO
        if(size == data.length) {
            throw new IllegalStateException("Queue is full");
        }
        // Front + size calculates the index of where the new element should go and
        // % operator ensures it is within the bounds of the array if not it will loop back around
        // to the start of the array
            int avail = (front + size) % data.length;
            data[avail] = e;
            size++;

        }


    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[front];
    }

    @Override
    public E dequeue() {
        // TODO
        if(isEmpty()) return null;
        E ans = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return ans;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; ++i) {
            E res = data[(front + i) % CAPACITY];
            sb.append(res);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> qq = new ArrayQueue<>();
        System.out.println(qq);

        int N = 10;
        for (int i = 0; i < N; ++i) {
            qq.enqueue(i);
        }
        System.out.println(qq);

        for (int i = 0; i < N / 2; ++i) qq.dequeue();
        System.out.println(qq);

    }
}
