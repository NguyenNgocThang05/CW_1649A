package onlineBookStore_backup;

public class Search {
    public static Order searchOrderById(Order_List allOrders, int searchOrderID) {
        Order foundOrder = null;

        LinkedQueueADT<Order> tempPendingQueue = new LinkedQueueADT<>();
        LinkedQueueADT<Order> pendingOrders = allOrders.getPendingOrderQueue();

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
