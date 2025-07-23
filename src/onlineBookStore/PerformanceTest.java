package onlineBookStore;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Helper_Functions.OrderList;
import onlineBookStore.Model.Book;
import onlineBookStore.Model.Customer;
import onlineBookStore.Model.Order;
public class PerformanceTest {

    public static void main(String[] args) {
        System.out.println("--- Online Bookstore Performance Test ---");

        // Initialize necessary components
        OrderList allOrders = new OrderList();

        // --- Test Case 1: Adding a large number of orders ---
        int numberOfOrdersToAdd = 10000; // You can adjust this number
        System.out.println("\nMeasuring time for adding " + numberOfOrdersToAdd + " orders...");

        long startTimeAdd = System.nanoTime(); // Record start time

        for (int i = 0; i < numberOfOrdersToAdd; i++) {
            // Create a dummy customer
            Customer customer = new Customer("Test Customer " + i, "123 Test St");

            // Create a dummy list of books for the order
            ArrayListADT<Book> booksForOrder = new ArrayListADT<>();
            // Add a few books to each order to simulate real-world orders
            booksForOrder.add(new Book("Book A", "Author X", 10.0, 100));
            booksForOrder.add(new Book("Book B", "Author Y", 15.0, 50));
            booksForOrder.add(new Book("Book C", "Author Z", 20.0, 200));

            // Create and add the order
            Order order = new Order(customer, booksForOrder);
            allOrders.addOrder(order); // This will enqueue the order
        }

        long endTimeAdd = System.nanoTime(); // Record end time
        long durationAdd = (endTimeAdd - startTimeAdd) / 1_000_000; // Convert to milliseconds
        System.out.println("Time taken to add " + numberOfOrdersToAdd + " orders: " + durationAdd + " ms");

        // --- Test Case 2: Searching for a large number of orders ---
        int numberOfSearches = 5000; // You can adjust this number
        System.out.println("\nMeasuring time for " + numberOfSearches + " searches...");

        long startTimeSearch = System.nanoTime(); // Record start time
        long endTimeSearch = System.nanoTime(); // Record end time
        long durationSearch = (endTimeSearch - startTimeSearch) / 1_000_000; // Convert to milliseconds
        System.out.println("Time taken for " + numberOfSearches + " searches: " + durationSearch + " ms");

        // --- Test Case 3: Completing a large number of orders ---
        int numberOfOrdersToComplete = 5000;
        System.out.println("\nMeasuring time for completing " + numberOfOrdersToComplete + " orders...");

        long startTimeComplete = System.nanoTime(); // Record start time

        for (int i = 0; i < numberOfOrdersToComplete; i++) {
            if (!allOrders.isEmpty()) {
                allOrders.finishOrder(); // This dequeues and re-enqueues
            } else {
                System.out.println("No more orders to complete.");
                break;
            }
        }

        long endTimeComplete = System.nanoTime(); // Record end time
        long durationComplete = (endTimeComplete - startTimeComplete) / 1_000_000; // Convert to milliseconds
        System.out.println("Time taken to complete " + numberOfOrdersToComplete + " orders: " + durationComplete + " ms");

        System.out.println("\n--- Performance Test Complete ---");
    }
}