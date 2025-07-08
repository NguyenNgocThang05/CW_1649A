package onlineBookStore_backup.Algorithm;

import onlineBookStore_backup.ADT.LinkedQueueADT;
import onlineBookStore_backup.Helper_Functions.Order_List;
import onlineBookStore_backup.Model.Order;

public class Search {
    public static Order searchOrderById(Order_List allOrders, int searchOrderID) {
        Order foundOrder = null; // Initialize a variable to store found order
                                 // Default as null (no order found yet)

        LinkedQueueADT<Order> tempPendingQueue = new LinkedQueueADT<>(); // Creates temporary LinkedQueueADT to hold pending orders while searching
        LinkedQueueADT<Order> pendingOrders = allOrders.getPendingOrdersQueue(); // Gets the queue of pending orders from the Order_List object

        int pendingSize = pendingOrders.size(); // Stores the current size of the pending orders queue

        // Loops through each order in the pending orders queue
        for (int i = 0; i < pendingSize; i++) {
            Order order = pendingOrders.poll(); // Removes and returns the order at the front of the pending queue
            if (order.getOrderID() == searchOrderID) { // Checks if the current order's ID matches the search ID
                foundOrder = order; // If a match is found, assign this order to foundOrder
            }
            tempPendingQueue.offer(order); // Adds the current order to the temporary pending queue to preserve its position
        }

        // Loops through the temporary pending queue to restore the original pending queue
        for (int i = 0; i < pendingSize; i++) {
            pendingOrders.offer(tempPendingQueue.poll()); // Removes an order from the temporary queue and adds it back to the original pending queue
        }



        // Checks if the order was not found in the pending order queue
        if (foundOrder == null) {
            LinkedQueueADT<Order> tempFinishedQueue = new LinkedQueueADT<>(); // Create a temporary LinkedQueueADT for finished orders
            LinkedQueueADT<Order> finishedOrders = allOrders.getFinishedOrdersQueue(); // Gets the queue of finished orders from the Order_List object

            int finishedSize = finishedOrders.size(); // Stores the current size of finished order queue

            // Loops through each order in the finished order queue
            for (int i =0; i < finishedSize; i++) {
                Order order = finishedOrders.poll(); // Removes and return the order at the front of the finished queue
                if (order.getOrderID() == searchOrderID) { // Checks if the current order's ID matches the search ID
                    foundOrder = order; // If a match is found, assign this order to foundOrder
                }
                tempFinishedQueue.offer(order); // Adds the current order to the temporary finished queue to preserve its position
            }

            // Loops through the temporary finished queue to restore the original finished queue
            for (int i = 0; i < finishedSize; i++) { // Removes an order from the temporary queue and adds it back to the original finished queue
                finishedOrders.offer(tempFinishedQueue.poll());
            }
        }

        return foundOrder; // Return the found order (or null if not found in either queue)
    }
}
