package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.OrderQueue; // Imports the custom OrderQueue class.
import onlineBookStore.Model.Order; // Imports the Order class.
import onlineBookStore.Algorithm.Sorting; // Imports the Sorting utility class.

public class Order_List {
    private final OrderQueue pendingOrders; // Declares an OrderQueue to manage pending orders.
    private final OrderQueue finishedOrders; // Declares an OrderQueue to manage completed orders.

    public Order_List() {
        this.pendingOrders = new OrderQueue(); // Initializes the pending orders queue.
        this.finishedOrders = new OrderQueue(); // Initializes the finished orders queue.
    }

    public void addOrder(Order order) {
        this.pendingOrders.enqueue(order); // Adds the new order to the pending queue's internal ArrayListADT.
        Sorting.insertionSortOrdersById(this.pendingOrders.queue); // Sorts the entire internal list of the pending queue by OrderID for binary search.
        System.out.println("Order " + order.getOrderID() + " added to pending."); // Confirms the order has been added.
    }

    public void finishOrder() {
        if (this.pendingOrders.isEmpty()) { // Checks if there are any orders in the pending queue.
            System.out.println("No orders to finish."); // Informs the user if no orders are pending.
            return; // Exits the method if the queue is empty.
        }

        Order completedOrder = this.pendingOrders.dequeue(); // Removes the oldest pending order (O(N) operation).
        completedOrder.markAsComplete(); // Updates the status of the dequeued order.

        this.finishedOrders.enqueue(completedOrder); // Adds the completed order to the finished orders queue.
        Sorting.insertionSortOrdersById(this.finishedOrders.queue); // Sorts the internal list of the finished orders queue by OrderID.

        System.out.println("Order " + completedOrder.getOrderID() + " has been finished."); // Confirms the order completion.
    }

    public void showOrderStatus() {
        if (this.pendingOrders.isEmpty() && this.finishedOrders.isEmpty()) { // Checks if both queues are entirely empty.
            System.out.println("No orders to display status for."); // Informs if there are no orders in the system.
            return; // Exits the method.
        }

        System.out.println("\nOrder Status"); // Prints a header for order status.

        System.out.println("\nPending Orders:"); // Header for pending orders.
        if (pendingOrders.isEmpty()) { // Checks if the pending queue is empty.
            System.out.println("No pending orders."); // Informs if no pending orders exist.
        } else {
            for (int i = 0; i < pendingOrders.size(); i++) { // Iterates through the pending orders.
                System.out.println(pendingOrders.get(i)); // Prints each pending order, displayed in sorted order.
            }
        }

        System.out.println("\nFinished Orders:"); // Header for finished orders.
        if (finishedOrders.isEmpty()) { // Checks if the finished queue is empty.
            System.out.println("No finished orders."); // Informs if no finished orders exist.
        } else {
            for (int i = 0; i < finishedOrders.size(); i++) { // Iterates through the finished orders.
                System.out.println(finishedOrders.get(i)); // Prints each finished order, displayed in sorted order.
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