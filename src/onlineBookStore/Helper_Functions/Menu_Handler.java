package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Model.Book;
import onlineBookStore.Model.Customer;
import onlineBookStore.Model.Order;
import onlineBookStore.Algorithm.Search;

import java.util.Scanner; // Imports Scanner for user input.

public class Menu_Handler {
    public void handleOrderBook(Scanner scanner, Book_Library availableBooks, Order_List allOrders) {
        availableBooks.list_all(); // Displays all books currently available in the library.
        ArrayListADT<Book> booksToOrder = new ArrayListADT<>(); // Creates a temporary list to hold books selected for the current order.
        int bookChoice; // Declares a variable for the chosen book number.

        if (availableBooks.getBookCount() == 0) { // Checks if there are any books in the library to order.
            System.out.println("Cannot place an order as there are no books available.");
            return; // Exits the method
        }

        while (true) { // Loop to allow multiple books to be added to a single order.
            System.out.print("Enter the number of the book you want to order (or press Enter to finish): ");
            String input = scanner.nextLine().trim(); // Reads user input for book choice.

            if (input.isEmpty()) { // Checks if the user wants to finish adding books.
                break; // Exits the book selection loop.
            }


            try {
                bookChoice = Integer.parseInt(input); // Converts user input to an integer.
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or press Enter to finish."); // Handles non-numeric input for book choice.
                continue; // Continues to the next iteration to ask for input again.
            }

            if (bookChoice < 1 || bookChoice > availableBooks.getBookCount()) { // Validates the chosen book number against available books.
                System.out.println("Invalid book number. Please try again."); // Informs the user of invalid book number.
                continue; // Continues to the next iteration.
            }

            Book selectedBook = availableBooks.getBook(bookChoice - 1); // Retrieves the selected book object (adjusting for 0-based index).

            if (selectedBook.getStock() == 0) { // Checks if the selected book is currently out of stock.
                System.out.println("Sorry, " + selectedBook.getTitle() + " is currently out of stock."); // Informs user if book is out of stock.
                continue; // Continues to the next iteration.
            }

            System.out.print("Enter quantity for " + selectedBook.getTitle() + ": "); // Prompts user for the desired quantity.
            int quantity; // Declares a variable for the desired quantity.
            try {
                quantity = Integer.parseInt(scanner.nextLine()); // Reads and converts the quantity input to an integer.
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number."); // Handles non-numeric quantity input.
                continue; // Continues to the next iteration.
            }

            if (quantity <= 0) { // Validates if the quantity is positive.
                System.out.println("Quantity must be greater than 0."); // Informs user of invalid quantity.
                continue; // Continues to the next iteration.
            }

            if (selectedBook.getStock() < quantity) { // Checks if there is enough stock for the requested quantity.
                System.out.println("Not enough stock for " + selectedBook.getTitle() + ". Available: " + selectedBook.getStock()); // Informs user about insufficient stock.
                continue; // Continues to the next iteration.
            }

            Book bookForOrder = new Book(selectedBook); // Creates a copy of the book for the order.
            bookForOrder.setQuantity(quantity); // Sets the specific quantity for this book within the order.
            booksToOrder.add(bookForOrder); // Adds the book (with its quantity) to the temporary order list.
            selectedBook.decrementStock(quantity); // Reduces the stock of the original book in the library.

            System.out.println(quantity + " of " + selectedBook.getTitle() + " added to your order."); // Confirms book addition to order.
        }

        if (booksToOrder.isEmpty()) { // Checks if any books were actually added to the order.
            System.out.println("No books were added to the order."); // Informs user if the order is empty.
            return; // Exits the method.
        }

        String customerName; // Declares a variable for customer's name.
        while (true) { // Loop to ensure a valid customer name is entered.
            System.out.print("Enter customer name: "); // Prompts for customer name.
            customerName = scanner.nextLine().trim(); // Reads and trims customer name input.
            if (customerName.isEmpty()) { // Checks if the name is empty.
                System.out.println("Customer name cannot be empty. Please enter a name."); // Informs user if name is empty.
            } else {
                break; // Exits loop if name is valid.
            }
        }

        String customerAddress; // Declares a variable for customer's address.
        while (true) { // Loop to ensure a valid customer address is entered.
            System.out.print("Enter customer address: "); // Prompts for customer address.
            customerAddress = scanner.nextLine().trim(); // Reads and trims customer address input.
            if (customerAddress.isEmpty()) { // Checks if the address is empty.
                System.out.println("Customer address cannot be empty. Please enter an address."); // Informs user if address is empty.
            } else {
                break; // Exits loop if address is valid.
            }
        }

        Customer customer = new Customer(customerName, customerAddress); // Creates a new Customer object.
        Order newOrder = new Order(customer, booksToOrder); // Creates a new Order object with customer and book list.
        allOrders.addOrder(newOrder); // Adds the created order to the main order management system.

        System.out.println("\nOrder placed successfully!"); // Confirms successful order placement.
        System.out.println(newOrder); // Prints the details of the newly placed order.
    }

    public void handleSearchOrderDetail(Scanner scanner, Order_List allOrders) {

        if (allOrders.getPendingOrdersQueue().isEmpty() && allOrders.getFinishedOrdersQueue().isEmpty()) { // Checks if there are any orders in the system to search.
            System.out.println("No orders to search yet."); // Informs the user if no orders exist.
            return; // Exits the method.
        }

        System.out.print("Enter Order ID to search: "); // Prompts the user to enter the Order ID.
        int targetOrderID; // Declares a variable for the target Order ID.

        try {
            targetOrderID = Integer.parseInt(scanner.nextLine()); // Converts user input to an integer.
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric Order ID."); // Handles non-numeric input for Order ID.
            return; // Exits the method.
        }

        Order foundOrder = Search.searchOrderById(allOrders, targetOrderID); // Calls the search method to find the order using binary search.

        if (foundOrder != null) { // Checks if an order was found by the search.
            System.out.println("\nOrder Found:"); // Informs the user an order was found.
            System.out.println(foundOrder); // Prints the details of the found order.
        } else {
            System.out.println("Order with ID " + targetOrderID + " not found."); // Informs user if the order was not found.
        }
    }

    public void handleDisplayOrderStatus(Order_List allOrders) {
        allOrders.showOrderStatus(); // shows all orders status
    }

    public void handleCompleteOrder(Order_List allOrders) {
        allOrders.finishOrder(); // Calls the method in Order_List to complete the oldest pending order.
    }

    public void handleSearchBook(Scanner scanner, Book_Library availableBooks) {
        System.out.print("Enter book title to search: ");
        String input = scanner.nextLine().trim(); // get user input

        if (input.isEmpty()) {
            System.out.println("Search cannot be empty");
            return;
        }

        boolean found = false;
        for (int i = 0; i < availableBooks.getBookCount(); i++) {
            Book book = availableBooks.getBook(i);
            // Comparison
            if (book.getTitle().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Found: " + book.toStockString()); // Display book details
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found matching: " + input);
        }
    }
}