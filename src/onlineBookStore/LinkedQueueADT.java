package onlineBookStore;

public class LinkedQueueADT<E> {
    private class Node<E> {
        // Data
        private E element; // The actual element stored in the node
        private Node<E> next;

        // Constructor
        public Node ( E element) {
            this.element = element;
            this.next = null;
        }
    }

    // Reference to the first node in the queue
    private Node<E> head;

    // Reference to the last node in the queue
    private Node<E> tail;

    // The current number of the elements in the queue
    private int size;

    // Constructor
    public LinkedQueueADT() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Add an element to the end of the queue
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.head == null && this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    // Remove and return the first element from the queue
    public E poll() {
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        E oldElement = this.head.element;
        Node<E> tempNode = this.head;
        this.head = this.head.next;
        tempNode.next = null;

        this.size--;
        return oldElement;
    }

    // Return the first element without removing it
    public E peek() {
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.head.element;
    }

    // Return the current number of elements in the queue
    public int size() {
        return this.size;
    }

    // Return true if empty, false otherwise
    public boolean isEmpty() {
        if (this.head == null && this.tail == null) {
            return true;
        }
        return false;
    }
}
