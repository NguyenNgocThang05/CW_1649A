package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.LinkedStackADT;
import onlineBookStore.ADT.OrderQueue; // Imports the custom OrderQueue class.
import onlineBookStore.Model.Order; // Imports the Order class.

public class Order_List {
    private final OrderQueue pendingOrders; // Declares an OrderQueue to manage pending orders.
    private final OrderQueue finishedOrders; // Declares an OrderQueue to manage completed orders.

    public Order_List() {
        this.pendingOrders = new OrderQueue(); // Initializes the pending orders queue.
        this.finishedOrders = new OrderQueue(); // Initializes the finished orders queue.
    }

    public void addOrder(Order order) {
        this.pendingOrders.enqueue(order); // Adds the new order to the pending queue's internal ArrayListADT.
        System.out.println("\nOrder " + order.getOrderID() + " added to pending."); // Confirms the order has been added.
    }

    public void finishOrder() {
        if (this.pendingOrders.isEmpty()) { // Checks if there are any orders in the pending queue.
            System.out.println("No orders to finish."); // Informs the user if no orders are pending.
            return; // Exits the method if the queue is empty.
        }

        Order completedOrder = this.pendingOrders.dequeue(); // Removes the oldest pending order
        completedOrder.markAsComplete(); // Updates the status of the dequeued order.

        this.finishedOrders.enqueue(completedOrder); // Adds the completed order to the finished orders queue.
        System.out.println("Order " + completedOrder.getOrderID() + " has been finished."); // Confirms the order completion.
    }

    public void showOrderStatus() {
        LinkedStackADT<Order> statusStack = new LinkedStackADT<>();

        if (this.pendingOrders.isEmpty() && this.finishedOrders.isEmpty()) { // Checks if both queues are entirely empty.
            System.out.println("No orders to display status for."); // Informs if there are no orders in the system.
            return; // Exits the method.
        }

        System.out.println("\nOrder Status"); // Prints a header for order status.

        System.out.println("\nPending Orders:"); // Header for pending orders.
        if (pendingOrders.isEmpty()) { // Checks if the pending queue is empty.
            System.out.println("No pending orders."); // Informs if no pending orders exist.
        } else {
            // Push all pending orders to stack
            for (int i = 0; i < pendingOrders.size(); i++) { // Iterates through the pending orders.
                statusStack.push(pendingOrders.get(i));
            }

            // Pop from stack to show the most recent first
            while (!statusStack.isEmpty()) {
                System.out.println(statusStack.pop());
            }
        }

        System.out.println("\nFinished Orders:"); // Header for finished orders.
        if (finishedOrders.isEmpty()) { // Checks if the finished queue is empty.
            System.out.println("No finished orders."); // Informs if no finished orders exist.
        } else {
            // Clear stack for reuse
            statusStack = new LinkedStackADT<>();

            // Push finished orders
            for (int i = 0; i < finishedOrders.size(); i++) { // Iterates through the finished orders.
                statusStack.push(finishedOrders.get(i)); // Prints each finished order, displayed in sorted order.
            }

            // Display the finished orders
            while (!statusStack.isEmpty()) {
                System.out.println(statusStack.pop());
            }
        }
        System.out.println("--------------------"); // Prints a footer.
    }

    public OrderQueue getPendingOrdersQueue() {
        return this.pendingOrders; // Returns the pending orders OrderQueue object.
    }

    public OrderQueue getFinishedOrdersQueue() {
        return this.finishedOrders; // Returns the finished orders OrderQueue object.
    }
}