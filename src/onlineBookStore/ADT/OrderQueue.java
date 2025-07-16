package onlineBookStore.ADT;

import onlineBookStore.Model.Order; // Imports the Order class for type safety within the queue.

public class OrderQueue {
    // Attributes
    public ArrayListADT<Order> queue; // Declares a public ArrayListADT to store the queue's elements, allowing external access for sorting and searching.

    // Constructor
    public OrderQueue() {
        queue = new ArrayListADT<>(); // Initializes the internal ArrayListADT when a new OrderQueue is created.
    }

    public void enqueue(Order order) {
        queue.add(order); // Appends the new order to the end of the internal ArrayListADT.
    }

    public Order dequeue() {
        if (queue.isEmpty()) { // Checks if the queue contains any elements.
            return null; // Returns null if the queue is empty.
        }
        Order firstOrder = queue.get(0); // Retrieves the first element from the ArrayListADT.
        queue.remove(0); // Removes the first element, which is an O(N) operation due to element shifting.
        return firstOrder; // Returns the retrieved (and now removed) order.
    }

    public Order peek() {
        if (queue.isEmpty()) { // Checks if the queue is empty.
            return null; // Returns null if the queue is empty.
        }
        return queue.get(0); // Returns the element at the front (index 0).
    }

    public boolean isEmpty() {
        return queue.isEmpty(); // return if the queue is empty
    }

    public int size() {
        return queue.size(); // returns the queue size
    }

    public Order get(int index) {
        return queue.get(index); // Retrieves the element at the given index from the ArrayListADT.
    }

    @Override // Overrides the default toString method from the Object class.
    public String toString() {
        return queue.toString(); // Returns the string representation of the internal ArrayListADT.
    }
}
