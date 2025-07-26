package onlineBookStore.Algorithm;

import onlineBookStore.ADT.LinkedQueueADT;
import onlineBookStore.Helper_Functions.OrderList;
import onlineBookStore.Model.Order;

public class Search {
    public static Order searchOrderById(OrderList allOrders, int targetOrderID) {
        LinkedQueueADT<Order> queue = allOrders.getAllOrders();
        int size = queue.size();

        // Binary search on the queue
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + right / 2;

            // Get the middle element by traversing from head
            Order midOrder = getOrderAt(queue, mid);
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

    private static Order getOrderAt(LinkedQueueADT<Order> queue, int index) {
        // Temporary queue to store dequeued elements
        LinkedQueueADT<Order> tempQueue = new LinkedQueueADT<>();
        Order result = null;

        // Dequeue until we reach the desired index
        for (int i = 0; i <= index; i++) {
            result = queue.poll();
            tempQueue.offer(result);
        }

        // Put all elements back in original queue
        while (!tempQueue.isEmpty()) {
            queue.offer(tempQueue.poll());
        }

        return result;
    }
}