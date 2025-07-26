package onlineBookStore.ADT;

public class LinkedQueueADT<E> {
    private class Node<E> {
        // data
        private E element; // the actual element stored in the node
        private Node<E> next;

        // constructor
        public Node ( E element ) {
            this.element = element;
            this.next = null;
        }
    }

    // Reference to the first node in the queue
    private Node<E> head;

    // Reference to the last node in the queue
    private Node<E> tail;

    // The current number of elements in the queue
    private int size;

    // constructor
    public LinkedQueueADT (){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void offer ( E element ) {
        // TODO: Implement the logic to add an element to the end of the queue.
        // Remember to create a new Node and update 'head', 'tail' pointers and 'size' appropriately.
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
        // TODO: Implement the logic to remove and return the first element from the queue.
        // Handle the case where the queue is empty by throwing an IllegalStateException.
        // Update 'head', 'tail' pointers (if necessary) and 'size'.
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty.");
        }

        E oldElement = this.head.element;
        Node<E> tempNode = this.head;
        this.head = this.head.next;
        tempNode.next = null;

        this.size--;
        return oldElement;
    }

    public E peek() {
        // TODO: Implement the logic to return the first element without removing it.
        // Handle the case where the queue is empty by throwing an IllegalStateException.
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.head.element;
    }

    public int size () {
        // TODO: Implement the logic to return the current number of elements in the queue.
        return this.size;
    }

    public boolean isEmpty () {
        // TODO: Implement the logic to check if the queue is empty.
        // Return true if empty, false otherwise.
        if (this.head == null && this.tail == null) {
            return true;
        }
        return false;
    }

    public String toString () {
        // TODO: Implement the logic to return a string representation of the queue.
        // For example, "[element1, element2, ..., lastElement]".
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