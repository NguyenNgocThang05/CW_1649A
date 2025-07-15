package onlineBookStore.ADT;

public class LinkedStackADT<E> {

    // Defines a private class. This class represents a single node that forms the stack
    private class Node<E> {
        // Attributes
        private E element; // A private variable to store the data element
        private Node<E> next; // A private variable to store a reference to the next node

        // Constructor
        public Node (E element) {
            this.element = element; // Initialize the element attribute with the provided 'element'
            this.next = null; // Initialize next to null, meaning it doesn't point to anything
        }
    }

    // Attributes
    private Node<E> top; // A private variable top which points to the most recently added element
    private int size; // A private int variable to keep track of the number of elements currently in the stack

    // Constructor
    public LinkedStackADT() {
        this.top = null; // Initialize top to null (meaning stack is empty)
        this.size = 0; // Initialize size to 0 as default (meaning stack is empty)
    }

    // Method to add an element to the stack
    public void push (E element) {
        Node<E> newNode = new Node<>(element); // Create a new Node with the provided element

        // Checks if the stack is empty
        if (this.top == null) {
            this.top = newNode; // If empty, the new node becomes the top of the stack
        } else { // If the stack is not empty
            newNode.next = this.top; // The new node's 'next' pointer is set to the current top node
            this.top = newNode; // The new node becomes the new top of the stack
        }

        this.size++; // Increment the stack's size
    }

    // Method to remove and return the element from the top of the stack
    public E pop() {

        // Check if the stack is empty
        if (this.top == null) {
            throw new IllegalStateException("Stack is empty");
        }

        E oldElement = this.top.element; // Stores the element of the current top node to be returned
        Node<E> tempNode = this.top; // Create a temporary reference to the current top node
        this.top = this.top.next; // Moves the 'top' pointer to the next node, removing the old top
        tempNode.next = null; // Disconnects the old top node from the stack

        this.size--; // Decrements the stack's size
        return oldElement; // Return the element that was removed
    }

    // Method to return the element at the top of the stack without removing it
    public E peek() {

        // Checks if the stack is empty
        if (this.top == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return this.top.element; // Returns the element of the current top node
    }

    // Method to return the number of elements
    public int size() {
        return this.size; // Returns the current value of the size attribute
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        // Checks if both the size is 0 and the top pointer is null
        if (this.size == 0 && this.top == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder(); // Creates a StringBuilder
        result.append("["); // Appends an opening bracket
        Node<E> tempNode = this.top; // Starts from the top of the stack

        // Continues as long as there are how many nodes left inside the stack
        while (tempNode != null) {
            result.append(tempNode.element); // Appends the element of the current node to the string
            // Checks if there is a next node
            if (tempNode.next != null) {
                result.append(", "); // Appends a comma and space if it's not the last element
            }
            tempNode = tempNode.next; // Moves to the next node in the stack
        }

        result.append("]"); // Appends a closing bracket
        return result.toString(); // Return the string
    }
}
