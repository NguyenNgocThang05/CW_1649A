package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.LinkedQueueADT;
import onlineBookStore.Model.Order;
import onlineBookStore.Model.OrderStatus;

public class OrderList {
    private final LinkedQueueADT<Order> allOrders;

    public OrderList() {
        this.allOrders = new LinkedQueueADT<>();
    }

    public void addOrder(Order addOrder) {
        if (addOrder == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        addOrder.setStatus(OrderStatus.PENDING);
        this.allOrders.offer(addOrder);
        System.out.println("\nOrder " + addOrder.getOrderID() + " added to pending.");
    }

    public void finishOrder() {
        if (this.allOrders.isEmpty()) {
            System.out.println("No orders to complete.");
            return;
        }

        Order oldestOrder = this.allOrders.peek();

        if (oldestOrder.getStatus() == OrderStatus.COMPLETE) {
            System.out.println("No pending orders to complete.");
            return;
        }

        oldestOrder.markAsComplete();
        Order completedOrder = this.allOrders.poll();
        this.allOrders.offer(completedOrder);
        System.out.println("Completed order: " + completedOrder.getOrderID());
    }

    public void showOrderStatus() {
        if (this.allOrders.isEmpty()) {
            System.out.println("No orders to display.");
            return;
        }

        System.out.println("\nOrder Status:");
        int size = allOrders.size();
        for (int i = 0; i < size; i++) {
            Order order = allOrders.poll();
            System.out.println("Order: " + order.getOrderID() + " | " +
                    "Customer: " + order.getCustomer().getName() + " | " +
                    "Status: " + order.getStatus());
            allOrders.offer(order);
        }
    }

    public LinkedQueueADT<Order> getAllOrders() {
        return this.allOrders;
    }

    public boolean isEmpty() {
        return this.allOrders.isEmpty();
    }
}