package onlineBookStore.Algorithm;

import onlineBookStore.ADT.OrderQueue; // Imports OrderQueue for search operations.
import onlineBookStore.Helper_Functions.Order_List; // Imports Order_List to access the order queues.
import onlineBookStore.Model.Order; // Imports the Order class.

public class Search {

    /**
     * Performs a binary search for an Order by its ID within a sorted OrderQueue.
     * @param orderQueue The OrderQueue (whose internal list must be sorted by Order ID).
     * @param targetOrderID The Order ID to find.
     * @return The found Order object, or null if not present.
     */
    private static Order binarySearch(OrderQueue orderQueue, int targetOrderID) {
        int low = 0; // Initializes the lower bound of the search range.
        int high = orderQueue.size() - 1; // Initializes the upper bound of the search range.

        while (low <= high) { // Continues the search as long as the lower bound is not past the upper bound.
            int mid = (high + low) / 2; // Calculates the middle index to divide the search range.

            Order midOrder = orderQueue.get(mid); // Retrieves the Order object at the middle index
            int midOrderID = midOrder.getOrderID(); // Gets the OrderID of the middle element.

            if (midOrderID == targetOrderID) { // Checks if the middle element's ID matches the target ID.
                return midOrder; // Returns the order if a match is found.
            } else if (midOrderID < targetOrderID) { // If the middle ID is smaller, the target must be in the right half.
                low = mid + 1; // Adjusts the lower bound to start searching from after the middle.
            } else { // If the middle ID is larger, the target must be in the left half.
                high = mid - 1; // Adjusts the upper bound to search up to before the middle.
            }
        }
        return null; // Returns null if the order is not found
    }

    /**
     * Searches for an Order by its ID across both pending and finished orders queues.
     * @param allOrders The Order_List containing both pending and finished orders.
     * @param targetOrderID The ID of the order to search for.
     * @return The found Order object.
     */
    public static Order searchOrderById(Order_List allOrders, int targetOrderID) {
        Order foundOrder; // Initializes a variable to store the search result.

        OrderQueue pendingOrders = allOrders.getPendingOrdersQueue(); // Gets the queue of pending orders.
        foundOrder = binarySearch(pendingOrders, targetOrderID); // Performs binary search on the pending orders queue.

        if (foundOrder == null) { // If the order was not found in the pending queue.
            OrderQueue finishedOrders = allOrders.getFinishedOrdersQueue(); // Gets the queue of finished orders.
            foundOrder = binarySearch(finishedOrders, targetOrderID); // Performs binary search on the finished orders queue.
        }

        return foundOrder; // Returns the order if found in either queue.
    }
}