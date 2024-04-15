package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
        tail = null;
        size -= 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = tail;
        for (int k = 0; k <= i; k++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        // TODO
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == 0) {
            addFirst(e);
        } else {
            Node<E> prev = tail;
            for (int k = 0; k < i; k++) {
                prev = prev.getNext();
            }
            prev.setNext(new Node<>(e, prev.getNext()));
            size++;
        }
    }

    @Override
    public E remove(int i) {
        // TODO
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == 0) {
            return removeFirst();
        } else {
            Node<E> prev = tail;
            for (int k = 0; k < i; k++) {
                prev = prev.getNext();
            }
            E data = prev.getNext().getData();
            if (prev.getNext() == tail) {
                tail = prev; // Update tail if the last element is removed
            }
            prev.setNext(prev.getNext().getNext());
            size--;
            return data;
        }

    }

    public void rotate() {
        // TODO
        if (tail != null) {
            tail = tail.getNext();
        }
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // TODO
        if (isEmpty()) {
            return null;
        }
        Node<E> head = tail.getNext();
        if (head == tail) {
            tail = null; // If only one element exists
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getData();
    }

    @Override
    public E removeLast() {
        // TODO
        return remove(size - 1);
    }

    @Override
    public void addFirst(E e) {
        // TODO
        if (isEmpty()) {
            tail = new Node<>(e, null);
            tail.setNext(tail); // Point to itself
        } else {
            Node<E> head = new Node<>(e, tail.getNext());
            tail.setNext(head);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        addFirst(e);
        tail = tail.getNext();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public E first(){
        if(isEmpty()){
            return null;
        }
        return tail.next.getData();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
