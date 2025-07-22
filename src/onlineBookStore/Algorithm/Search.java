package onlineBookStore.Algorithm;

import onlineBookStore.ADT.OrderQueue; // Imports OrderQueue for search operations.
import onlineBookStore.Helper_Functions.OrderList; // Imports Order_List to access the order queues.
import onlineBookStore.Model.Order; // Imports the Order class.

public class Search {
    private static Order binarySearch(OrderQueue orderQueue, int targetOrderID) {
        int left = 0; // Initializes the lower bound of the search range.
        int right = orderQueue.size() - 1; // Initializes the upper bound of the search range.

        while (left <= right) { // Continues the search as long as the lower bound is not past the upper bound.
            int mid = (right + left) / 2; // Calculates the middle index to divide the search range.

            Order midOrder = orderQueue.get(mid); // Retrieves the Order object at the middle index
            int midOrderID = midOrder.getOrderID(); // Gets the OrderID of the middle element.

            if (midOrderID == targetOrderID) { // Checks if the middle element's ID matches the target ID.
                return midOrder; // Returns the order if a match is found.
            } else if (midOrderID < targetOrderID) { // If the middle ID is smaller, the target must be in the right half.
                left = mid + 1; // Adjusts the lower bound to start searching from after the middle.
            } else { // If the middle ID is larger, the target must be in the left half.
                right = mid - 1; // Adjusts the upper bound to search up to before the middle.
            }
        }
        return null; // Returns null if the order is not found
    }

    public static Order searchOrderById(OrderList allOrders, int targetOrderID){
        // Retrieve the OrderQueue from Order_List
        OrderQueue queue = allOrders.getAllOrders();
        return binarySearch(queue, targetOrderID);
    }
}
