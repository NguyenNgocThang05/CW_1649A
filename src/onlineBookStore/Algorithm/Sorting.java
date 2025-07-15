package onlineBookStore.Algorithm;


import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Model.Book;
import onlineBookStore.Model.Order;

public class Sorting {
    // Insertion sort to sort books by title (A-Z)
    public static void insertionSortBook(ArrayListADT<Book> books) {
        // Starts a loop from the second element (index 1) to the end of the list. The first element (index 0) is considered stored
        for (int i = 1; i < books.size(); i++) {
            Book current = books.get(i); // Stores to the temporary current variable
            int j = i - 1; // Initialize an index j to the last element of the sorted portion

            // Shifts elements in the sorted portion to the right
            // If the element is greater than the current element, making space for current
            while (j >= 0 && books.get(j).getTitle().compareTo(current.getTitle()) > 0) {
                books.set(j + 1, books.get(j)); // Shifts the element at index j one position to the right
                j--; // Decrements j to move to the next element on the left in the sorted portion
            }

            books.set(j + 1, current); // Inserts the current element into its correct sorted position
        }
    }

    /**
     * Sorts an ArrayListADT of Order objects by their OrderID using the Insertion Sort algorithm.
     * @param orders The ArrayListADT of Order objects to be sorted.
     */
    public static void insertionSortOrdersById(ArrayListADT<Order> orders) {
        for (int i = 1; i < orders.size(); i++) { // Starts iterating from the second element, as the first is considered a sorted subarray of one.
            Order current = orders.get(i); // Stores the current element to be inserted into its correct position.
            int j = i - 1; // Initializes a pointer to the last element of the sorted subarray.

            while (j >= 0 && orders.get(j).getOrderID() > current.getOrderID()) { // Compares the current element with elements in the sorted subarray.
                orders.set(j + 1, orders.get(j)); // Shifts elements larger than 'current' one position to the right.
                j--; // Moves the pointer to the left to compare with the next element in the sorted subarray.
            }
            orders.set(j + 1, current); // Inserts the 'current' element into its correct sorted position.
        }
    }
}
