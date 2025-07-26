package onlineBookStore.Algorithm;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Helper_Functions.OrderList;
import onlineBookStore.Model.Order;

public class Search {
    public static Order searchOrderById(OrderList allOrders, int targetOrderID) {
        // Validate input
        if (allOrders == null || allOrders.isEmpty()) {
            return null;
        }

        ArrayListADT<Order> orders = allOrders.getAllOrders();

        // Binary search implementation
        int left = 0;
        int right = orders.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Order midOrder = orders.get(mid);
            int midOrderID = midOrder.getOrderID();

            if (midOrderID == targetOrderID) {
                return midOrder;
            } else if (midOrderID < targetOrderID) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }
}