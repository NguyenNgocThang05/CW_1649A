package onlineBookStore.ADT;

import onlineBookStore.Model.Order; // Imports the Order class for type safety within the queue.

public class OrderQueue {
    // Attributes
    public ArrayListADT<Order> queue; // Declares a public ArrayListADT to store the queue's elements, allowing external access for sorting and searching.

    // Constructor
    public OrderQueue() {
        queue = new ArrayListADT<>(); // Initializes the internal ArrayListADT when a new OrderQueue is created.
    }

    /**
     * Adds an order to the back of the queue (enqueue operation).
     * @param order The order to be added.
     */
    public void enqueue(Order order) {
        queue.add(order); // Appends the new order to the end of the internal ArrayListADT.
    }

    /**
     * Removes and returns the order from the front of the queue (dequeue operation).
     * @return The first order in the queue, or null if empty.
     */
    public Order dequeue() {
        if (queue.isEmpty()) { // Checks if the queue contains any elements.
            return null; // Returns null if the queue is empty.
        }
        Order firstOrder = queue.get(0); // Retrieves the first element from the ArrayListADT.
        queue.remove(0); // Removes the first element, which is an O(N) operation due to element shifting.
        return firstOrder; // Returns the retrieved (and now removed) order.
    }

    /**
     * Returns the order at the front of the queue without removing it.
     * @return The first order, or null if empty.
     */
    public Order peek() {
        if (queue.isEmpty()) { // Checks if the queue is empty.
            return null; // Returns null if the queue is empty.
        }
        return queue.get(0); // Returns the element at the front (index 0).
    }

    /**
     * Checks if the queue contains no elements.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty(); // Delegates the emptiness check to the underlying ArrayListADT.
    }

    /**
     * Returns the number of elements in the queue.
     * @return The number of elements.
     */
    public int size() {
        return queue.size(); // Delegates the size query to the underlying ArrayListADT.
    }

    /**
     * Gets an element at a specific index, enabling O(1) random access for binary search.
     * @param index The index of the element.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Order get(int index) {
        return queue.get(index); // Retrieves the element at the given index from the ArrayListADT.
    }

    /**
     * Provides a string representation of the queue's contents.
     * @return A string representation.
     */
    @Override // Overrides the default toString method from the Object class.
    public String toString() {
        return queue.toString(); // Returns the string representation of the internal ArrayListADT.
    }
}
