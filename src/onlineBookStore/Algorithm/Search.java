package onlineBookStore.Algorithm;

import onlineBookStore.ADT.LinkedQueueADT;
import onlineBookStore.Helper_Functions.OrderList;
import onlineBookStore.Model.Order;

public class Search {
    public static Order searchOrderById(OrderList allOrders, int targetOrderID) {
        LinkedQueueADT<Order> orders = allOrders.getAllOrders();
        int size = orders.size();

        for (int i = 0; i < size; i++) {
            Order current = orders.poll();
            if (current.getOrderID() == targetOrderID) {
                // Put the order back in the queue (since we removed it)
                orders.offer(current);
                // Put remaining elements back
                for (int j = i + 1; j < size; j++) {
                    orders.offer(orders.poll());
                }
                return current;
            }
            // Put the order back in the queue
            orders.offer(current);
        }
        return null; // Not found
    }
}