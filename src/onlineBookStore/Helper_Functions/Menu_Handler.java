package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Model.Book;
import onlineBookStore.Model.Customer;
import onlineBookStore.Model.Order;
import onlineBookStore.Algorithm.Search;

import java.util.Scanner;

public class Menu_Handler {
    public void handleOrderBook(Scanner scanner, Book_Library availableBooks, Order_List allOrders) {
        // Display available books
        availableBooks.list_all();
        ArrayListADT<Book> booksToOrder = new ArrayListADT<>();
        int bookChoice;

        // Check if the library is empty
        if (availableBooks.getLibrarySize() == 0) {
            System.out.println("Cannot place an order as there are no books available.");
            return;
        }

        while (true) {
            // Book Selection
            System.out.print("Enter book number to add to your order (1-" + availableBooks.getLibrarySize() + ", or press Enter to finish): ");
            String bookInput = scanner.nextLine().trim();

            // Check if the input is empty
            if (bookInput.isEmpty()) {
                break;
            }

            try {
                bookChoice = Integer.parseInt(bookInput); // Converting the input into integer
                // Check if the book selection is within range
                if (bookChoice < 1 || bookChoice > availableBooks.getLibrarySize()) {
                    System.out.println("Invalid book selection. Try again.");
                    continue;
                }
            } catch (NumberFormatException e) { // Catch an error if the input is non-numeric
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            Book selectedBook = availableBooks.getBook(bookChoice - 1);

            // Quantity selection
            System.out.print("Enter quantity for " + selectedBook.getTitle() + ": ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim()); // Converting quantity input into integer
                // Check if quantity is below or equal to 0
                if (quantity <= 0) {
                    System.out.println("Quantity must be positive. Try again.");
                    continue;
                }
                // Check if the quantity is greater than the book's stock
                if (quantity > selectedBook.getStock()) {
                    System.out.println("Not enough stock. Available: " + selectedBook.getStock());
                    continue;
                }
            } catch (NumberFormatException e) { // Catch an error if the input is non-numeric
                System.out.println("Invalid quantity. Try again.");
                continue;
            }

            // Add book to order
            Book orderedBook = new Book(selectedBook);
            orderedBook.setQuantity(quantity);
            booksToOrder.add(orderedBook);
            selectedBook.decrementStock(quantity);
            System.out.println(quantity + " of " + selectedBook.getTitle() + " added to your order.");
        }

        if (booksToOrder.isEmpty()) {
            System.out.println("No books were added. Order cancelled.");
            return;
        }

        // Customer details
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        while (customerName.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter customer name: ");
            customerName = scanner.nextLine().trim();
        }

        System.out.print("Enter customer address: ");
        String customerAddress = scanner.nextLine().trim();
        while (customerAddress.isEmpty()) {
            System.out.println("Address cannot be empty.");
            System.out.print("Enter customer address: ");
            customerAddress = scanner.nextLine().trim();
        }

        // Create and save the order
        Customer customer = new Customer(customerName, customerAddress);
        Order newOrder = new Order(customer, booksToOrder);
        allOrders.addOrder(newOrder);

        System.out.println("Order created successfully:");
        System.out.println(newOrder);
    }

    public void handleSearchOrderDetail(Scanner scanner, Order_List allOrders) {

        if (allOrders.getPendingOrdersQueue().isEmpty() && allOrders.getFinishedOrdersQueue().isEmpty()) { // Checks if there are any orders in the system to search.
            System.out.println("No orders to search yet."); // Informs the user if no orders exist.
            return; // Exits the method.
        }

        while (true) {
            System.out.print("Enter Order ID to search (or press Enter to go back to menu): "); // Prompts the user to enter the Order ID.
            String inputOrderID = scanner.nextLine().trim();

            if (inputOrderID.isEmpty()) {
                break;
            }

            try {
                int targetOrderID = Integer.parseInt(inputOrderID); // Converts user input to an integer.
                Order foundOrder = Search.searchOrderById(allOrders, targetOrderID); // Calls the search method to find the order using binary search.

                if (targetOrderID <= 0) {
                    System.out.println("Order ID must be a positive number");
                    continue;
                }

                if (foundOrder != null) { // Checks if an order was found by the search.
                    System.out.println("\nOrder Found:"); // Informs the user an order was found.
                    System.out.println(foundOrder); // Prints the details of the found order.
                } else {
                    System.out.println("Order with ID " + targetOrderID + " not found."); // Informs user if the order was not found.
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric Order ID."); // Handles non-numeric input for Order ID.
            }
        }
    }

    public void handleDisplayOrderStatus(Order_List allOrders) {
        allOrders.showOrderStatus(); // shows all orders status
    }

    public void handleCompleteOrder(Order_List allOrders) {
        allOrders.finishOrder(); // Calls the method in Order_List to complete the oldest pending order.
    }

    public void handleSearchBook(Scanner scanner, Book_Library availableBooks) {
        while (true) {
            System.out.print("Enter book title to search (or press Enter to go back to menu): ");
            String inputBookTitle = scanner.nextLine().trim(); // get user input

            if (inputBookTitle.isEmpty()) {
                break;
            }

            boolean found = false;
            for (int i = 0; i < availableBooks.getLibrarySize(); i++) {
                Book book = availableBooks.getBook(i);
                // Comparison
                if (book.getTitle().toLowerCase().contains(inputBookTitle.toLowerCase())) {
                    System.out.println("Found: " + book.toStockString()); // Display book details
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No books found matching: " + inputBookTitle);
            }
        }
    }
}