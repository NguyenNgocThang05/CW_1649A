package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.OrderQueue;
import onlineBookStore.Model.Order;
import onlineBookStore.Model.OrderStatus;

public class OrderList {
    private final OrderQueue allOrders; // Declares an OrderQueue to manage pending orders.

    public OrderList() {
        this.allOrders = new OrderQueue();
    }

    public void addOrder(Order addOrder) {
        addOrder.setStatus(OrderStatus.PENDING); // Adds the new order to the pending queue's internal ArrayListADT.
        this.allOrders.enqueue(addOrder);
        System.out.println("\nOrder " + addOrder.getOrderID() + " added to pending."); // Confirms the order has been added.
    }

    public void finishOrder() {
        if (this.allOrders.isEmpty()) {
            System.out.println("No orders to complete.");
            return;
        }

        // Get the oldest order (front of queue)
        Order oldestOrder = this.allOrders.peek();

        if (oldestOrder.getStatus() == OrderStatus.COMPLETE) {
            System.out.println("No pending orders to complete.");
            return;
        }

        // Mark as complete and dequeue
        oldestOrder.markAsComplete();
        Order completedOrder = this.allOrders.dequeue();

        // Re-enqueue to keep in system
        this.allOrders.enqueue(completedOrder);
        System.out.println("Completed order: " + completedOrder.getOrderID());
    }

    public void showOrderStatus() {
        if (this.allOrders.isEmpty()) {
            System.out.println("No orders to display.");
            return;
        }

        System.out.println("\nOrder Status:");
        for (int i = 0; i < allOrders.size(); i++) {
            Order order = allOrders.get(i);
            System.out.println("Order: " + order.getOrderID() + " | " +
                                "Customer: " + order.getCustomer().getName() + " | " +
                                "Status: " + order.getStatus());
        }
    }

    public OrderQueue getAllOrders() {
        return this.allOrders;
    }
}