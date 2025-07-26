package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.ADT.LinkedStackADT;
import onlineBookStore.Model.Book;
import onlineBookStore.Model.Customer;
import onlineBookStore.Model.Order;
import onlineBookStore.Algorithm.Search;

import java.util.Scanner;

public class MenuManager {
    public static void handleOrderBook(Scanner scanner, BookLibrary availableBooks, OrderList allOrders) {
        // Display available books
        availableBooks.listAll();
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
            String quantityInput = scanner.nextLine().trim();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityInput); // Converting quantity input into integer
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

    public static void handleSearchOrderDetail(Scanner scanner, OrderList allOrders, LinkedStackADT<Order> orderSearchHistory) {

        if (allOrders.getAllOrders().isEmpty()) { // Checks if there are any orders in the system to search.
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

                    // Add to search history
                    orderSearchHistory.push(foundOrder);
                } else {
                    System.out.println("Order with ID " + targetOrderID + " not found."); // Informs user if the order was not found.
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric Order ID."); // Handles non-numeric input for Order ID.
            }
        }
    }

    public static void handleDisplayOrderStatus(OrderList allOrders) {
        allOrders.showOrderStatus(); // shows all orders status
    }

    public static void handleCompleteOrder(OrderList allOrders) {
        allOrders.finishOrder(); // Calls the method in Order_List to complete the oldest pending order.
    }

    public static void handleSearchHistory (Scanner scanner, LinkedStackADT<Order> orderSearchHistory) {
        // If no searches was made
        if (orderSearchHistory.isEmpty()) {
            System.out.println("\nNo search history found.");
            return;
        }

        while (true) {
            System.out.println("\nRecently searched order: " + orderSearchHistory.peek().getOrderID());
            System.out.println("1. Undo last search");
            System.out.println("2. Show full history");
            System.out.println("3. Go back to main menu");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    Order undoSearch = orderSearchHistory.pop();    // Pops the most recently search order to undo
                    System.out.println("\nUndid search for Order ID: " + undoSearch.getOrderID());
                    break;

                case "2":
                    System.out.println("\nAll search history:");
                    System.out.println(orderSearchHistory);   // Prints all searched orders
                    break;

                case "3":
                    return; // returns back to the main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (orderSearchHistory.isEmpty()) {
                System.out.println("No more search history left to undo");  // Tells the user no more undo can be done
                break;
            }
        }
    }
}