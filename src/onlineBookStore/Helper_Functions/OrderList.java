package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Model.Order;
import onlineBookStore.Model.OrderStatus;

public class OrderList {
    private final ArrayListADT<Order> orders;
    private int pendingCount;

    public OrderList() {
        this.orders = new ArrayListADT<>();
        this.pendingCount = 0;
    }

    public void addOrder(Order newOrder) {
        if (newOrder == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        newOrder.setStatus(OrderStatus.PENDING);
        orders.add(newOrder);
        pendingCount++;
        System.out.printf("\nOrder %d added to pending orders. (%d pending)%n",
                newOrder.getOrderID(), pendingCount);
    }

    public Order finishOrder() {
        if (pendingCount == 0) {
            System.out.println("No pending orders to complete.");
            return null;
        }

        // Find the oldest pending order
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.getStatus() == OrderStatus.PENDING) {
                order.markAsComplete();
                pendingCount--;
                System.out.printf("Completed order: %d (%d remaining pending)%n",
                        order.getOrderID(), pendingCount);
                return order;
            }
        }

        System.out.println("No pending orders found (inconsistent state).");
        return null;
    }

    public void showOrderStatus() {
        if (orders.isEmpty()) {
            System.out.println("No orders to display.");
            return;
        }

        System.out.println("\nOrder Status Summary:");
        System.out.printf("Total orders: %d (Pending: %d, Completed: %d)%n",
                orders.size(), pendingCount, orders.size() - pendingCount);

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.printf("%d. Order ID: %d | Customer: %s | Status: %s%n",
                    i + 1,
                    order.getOrderID(),
                    order.getCustomer().getName(),
                    order.getStatus());
        }
    }

    public ArrayListADT<Order> getAllOrders() {
        return orders;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }
}