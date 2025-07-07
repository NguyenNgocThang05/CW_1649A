package onlineBookStore_backup;

public class LinkedQueueADT<E> {
    private class Node<E> {
        // Data
        private E element;
        private Node<E> next;

        // Constructor
        public Node (E element) {
            this.element = element;
            this.next = null;
        }
    }

    private Node<E> head;

    private Node<E> tail;

    private int size;

    // Constructor
    public LinkedQueueADT() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void offer (E element) {
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

    public E poll() {
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        E oldElement = this.head.element;
        Node<E> tempNode = this.head;
        this.head = this.head.next;

        if (this.head == null) {
            this.tail = null;
        }

        tempNode.next = null;

        this.size--;
        return oldElement;
    }

    public E peek() {
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        return this.head.element;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        if (this.head == null && this.tail == null) {
            return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        Node<E> tempNode = this.head;

        while (tempNode != null) {
            result.append(tempNode.element);
            if (tempNode.next != null) {
                result.append(", ");
            }
            tempNode = tempNode.next;
        }

        result.append("]");
        return result.toString();
    }

}
