package onlineBookStore_backup.ADT;

public class LinkedQueueADT<E> {
    private class Node<E> {
        // Attributes
        private E element; // A private variable to store the data element
        private Node<E> next; // A private variable to store a reference to the next node

        // Constructor
        public Node (E element) {
            this.element = element; // Initialize the element attribute with the provided 'element'
            this.next = null; // Initialize 'next' to null, meaning it doesn't point to anything
        }
    }

    // Attributes
    private Node<E> head; // A private variable head which points to the oldest element of the queue

    private Node<E> tail; // A private variable tail which points to the newest element of the queue

    private int size; // A private int variable to keep track of the number of the queue

    // Constructor
    public LinkedQueueADT() {
        this.head = null; // Initialize head to null, meaning the queue is empty
        this.tail = null; // Initialize tail to null, meaning the queue is empty
        this.size = 0; // Initialize size to 0, meaning the queue is empty
    }

    // Method to add an element to the back of the queue
    public void offer (E element) {
        Node<E> newNode = new Node<>(element); // Creates a new Node with the provided element

        // Checks if the queue is empty
        if (this.head == null && this.tail == null) {
            this.head = newNode; // If empty, the new Node becomes both head and tail
            this.tail = newNode;
        } else { // If the queue is not empty
            this.tail.next = newNode; // The current tail's 'next' point is set to the new node
            this.tail = newNode; // The new node becomes the new tail of the queue
        }

        this.size++; // Increments the queue's size
    }

    // Method to remove and return the element from the front of the queue
    public E poll() {
        // Checks if the queue is empty
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        E oldElement = this.head.element; // Stores the element of the current head node to be returned
        Node<E> tempNode = this.head; // Create a temporary reference to the current head node
        this.head = this.head.next; // Moves the 'head' pointer to the next node

        // If the head becomes null after removing (meaning the queue is now empty)
        if (this.head == null) {
            this.tail = null; // Set tail to null as well
        }

        tempNode.next = null; // Disconnects the old head node from the queue

        this.size--; // Decrements the queue's size
        return oldElement; // Return the element that was removed
    }

    // Method to return the head element without removing it
    public E peek() {
        // Checks if the queue is empty
        if (this.head == null && this.tail == null) {
            throw new IllegalStateException("Queue is empty");
        }

        return this.head.element; // Returns the head element value of the queue
    }

    // Method to return the number of elements in the queue
    public int size() {
        return this.size;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        if (this.head == null && this.tail == null) {
            return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder(); // Creates a StringBuilder
        result.append("["); // Append an opening bracket
        Node<E> tempNode = this.head; // Starts from the head node of the queue

        // Continues as long as there are how many nodes left in the queue
        while (tempNode != null) {
            result.append(tempNode.element); // Appends the element of the current node to the string
            // Checks if there is a next node
            if (tempNode.next != null) {
                result.append(", "); // Appends a comma and space if it's not the last element
            }
            tempNode = tempNode.next; // Moves to the next node in the queue
        }

        result.append("]"); // Append a closing bracket
        return result.toString(); // Return the string
    }

}
