package onlineBookStore_backup.Algorithm;

import onlineBookStore_backup.ADT.LinkedQueueADT;
import onlineBookStore_backup.Helper_Functions.Order_List;
import onlineBookStore_backup.Model.Order;

public class Search {
    public static Order searchOrderById(Order_List allOrders, int searchOrderID) {
        Order foundOrder = null;

        LinkedQueueADT<Order> tempPendingQueue = new LinkedQueueADT<>();
        LinkedQueueADT<Order> pendingOrders = allOrders.getPendingOrdersQueue();

        int pendingSize = pendingOrders.size();

        for (int i = 0; i < pendingSize; i++) {
            Order order = pendingOrders.poll();
            if (order.getOrderID() == searchOrderID) {
                foundOrder = order;
            }
            tempPendingQueue.offer(order);
        }

        for (int i = 0; i < pendingSize; i++) {
            pendingOrders.offer(tempPendingQueue.poll());
        }

        if (foundOrder == null) {
            LinkedQueueADT<Order> tempFinishedQueue = new LinkedQueueADT<>();

            LinkedQueueADT<Order> finishedOrders = allOrders.getFinishedOrdersQueue();

            int finishedSize = finishedOrders.size();

            for (int i =0; i < finishedSize; i++) {
                Order order = finishedOrders.poll();
                if (order.getOrderID() == searchOrderID) {
                    foundOrder = order;
                }
                tempFinishedQueue.offer(order);
            }

            for (int i = 0; i < finishedSize; i++) {
                finishedOrders.offer(tempFinishedQueue.poll());
            }
        }

        return foundOrder;
    }
}
