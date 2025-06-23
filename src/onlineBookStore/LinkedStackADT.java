package onlineBookStore;

public class LinkedStackADT<E> {
    private class Node<E> {
        // Attributes
        private E element;
        private Node<E> next;

        // Constructor
        public Node (E element) {
            this.element = element;
            this.next = null;
        }
    }

    // Attributes
    private Node<E> top;
    private int size;

    // Constructor
    public LinkedStackADT() {
        this.top = null;
        this.size = 0;
    }

    public void push(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.top == null) {
            this.top = newNode;
        } else {
            newNode.next = this.top;
            this.top = newNode;
        }

        this.size++;
    }

    public E pop() {
        if (this.top == null) {
            throw new IllegalStateException("Stack is empty");
        }

        E oldElement = this.top.element;
        Node<E> tempNode = this.top;
        this.top = this.top.next;
        tempNode.next = null;

        this.size--;
        return oldElement;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        if (this.top == null && this.size == 0) {
            return true;
        }
        return false;
    }
}
