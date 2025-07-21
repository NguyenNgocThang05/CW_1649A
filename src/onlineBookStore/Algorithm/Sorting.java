package onlineBookStore.Algorithm;


import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Model.Book;

public class Sorting {
    // Insertion sort to sort books by title (A-Z)
    public static void insertionSortBook(ArrayListADT<Book> books) {
        // Starts a loop from the second element (index 1) to the end of the list. The first element (index 0) is considered sorted
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
}
