package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.ADT.LinkedStackADT;
import onlineBookStore.Model.Book;
import onlineBookStore.Model.Customer;
import onlineBookStore.Model.Order;
import onlineBookStore.Algorithm.Search;

import java.util.Scanner; // Imports Scanner for user input.

public class Menu_Handler {
    /**
     * Manages the process of a customer ordering books, including selection, quantity, and customer details.
     * @param scanner The Scanner for user input.
     * @param availableBooks The library of available books.
     * @param allOrders The system's central order list.
     */
    public void handleOrderBook(Scanner scanner, Book_Library availableBooks, Order_List allOrders) {
        availableBooks.list_all(); // Displays all books currently available in the library.
        ArrayListADT<Book> booksToOrder = new ArrayListADT<>(); // Creates a temporary list to hold books selected for the current order.

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

            int bookChoice; // Declares a variable for the chosen book number.
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

            if (selectedBook.getStock() <= 0) { // Checks if the selected book is currently out of stock.
                System.out.println("Sorry, \"" + selectedBook.getTitle() + "\" is currently out of stock."); // Informs user if book is out of stock.
                continue; // Continues to the next iteration.
            }

            System.out.print("Enter quantity for \"" + selectedBook.getTitle() + "\": "); // Prompts user for the desired quantity.
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
                System.out.println("Not enough stock for \"" + selectedBook.getTitle() + "\". Available: " + selectedBook.getStock()); // Informs user about insufficient stock.
                continue; // Continues to the next iteration.
            }

            Book bookForOrder = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), selectedBook.getPrice(), selectedBook.getStock()); // Creates a copy of the book for the order.
            bookForOrder.setQuantity(quantity); // Sets the specific quantity for this book within the order.
            booksToOrder.add(bookForOrder); // Adds the book (with its quantity) to the temporary order list.

            selectedBook.decrementStock(quantity); // Reduces the stock of the original book in the library.

            System.out.println(quantity + " of \"" + selectedBook.getTitle() + "\" added to your order."); // Confirms book addition to order.
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
        allOrders.addOrder(newOrder); // Adds the newly created order to the main order management system.

        System.out.println("\nOrder placed successfully!"); // Confirms successful order placement.
        System.out.println(newOrder); // Prints the details of the newly placed order.
    }

    /**
     * Handles searching for order details by their unique Order ID.
     * @param scanner The Scanner for user input.
     * @param allOrders The system's central order list.
     * @param viewedOrderHistory The stack to keep track of recently viewed orders.
     */
    public void handleSearchOrderDetail(Scanner scanner, Order_List allOrders, LinkedStackADT<Order> viewedOrderHistory) {

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

            if (viewedOrderHistory.size() >= 5) { // Manages the search history stack to keep a maximum of 5 entries.
                LinkedStackADT<Order> temp = new LinkedStackADT<>(); // Creates a temporary stack.

                while (!viewedOrderHistory.isEmpty()) { // Pops all elements from history to the temporary stack.
                    temp.push(viewedOrderHistory.pop());
                }

                if (!temp.isEmpty()) { // Removes the oldest element if the temporary stack is not empty.
                    temp.pop();
                }

                while (!temp.isEmpty()) { // Pushes elements back from the temporary stack to restore history.
                    viewedOrderHistory.push(temp.pop());
                }
            }

            viewedOrderHistory.push(foundOrder); // Adds the newly found order to the top of the search history stack.

            System.out.println("\nOrder added to your search history."); // Confirms addition to history.
        } else {
            System.out.println("Order with ID " + targetOrderID + " not found."); // Informs user if the order was not found.
        }
    }

    /**
     * Displays the current status of all pending and finished orders in the system.
     * @param allOrders The system's central order list.
     */
    public void handleDisplayOrderStatus(Order_List allOrders) {
        allOrders.showOrderStatus(); // Delegates the display logic to the Order_List class.
    }

    /**
     * Handles the completion of an order, following the FIFO principle.
     * @param allOrders The system's central order list.
     */
    public void handleCompleteOrder(Order_List allOrders) {
        allOrders.finishOrder(); // Calls the method in Order_List to complete the oldest pending order.
    }

    /**
     * Displays the user's recently searched order history, showing up to the 5 most recent searches.
     * @param historyStack The LinkedStackADT containing the search history.
     */
    public void handleViewOrderHistory(LinkedStackADT<Order> historyStack) {
        System.out.println("\nRecently Searched Order History"); // Prints a header for the history display.
        if (historyStack.isEmpty()) { // Checks if there are any orders in the search history.
            System.out.println("No orders in your search history yet."); // Informs the user if history is empty.
            return; // Exits the method.
        }

        ArrayListADT<Order> tempHistoryList = new ArrayListADT<>(); // Creates a temporary list to reverse the stack order for display.

        while (!historyStack.isEmpty()) { // Pops all elements from the history stack and adds them to the temporary list.
            tempHistoryList.add(historyStack.pop());
        }

        int count = 0; // Initializes a counter for displayed orders.
        for (int i = tempHistoryList.size() - 1; i >= 0; i--) { // Iterates the temporary list from end to beginning to show most recent first.
            if (count >= 5) { // Limits the display to only the 5 most recent entries.
                break; // Exits the loop after displaying 5 orders.
            }
            Order order = tempHistoryList.get(i); // Retrieves an order from the temporary list.
            System.out.println("Search #" + (count + 1) + ": Order ID " + order.getOrderID()); // Displays the search number and order ID.
            System.out.println(order + "\n"); // Displays the full details of the order.
            count++; // Increments the counter.
        }

        for (int i = tempHistoryList.size() - 1; i >= 0; i--) { // Pushes all elements back onto the history stack to restore its original state.
            historyStack.push(tempHistoryList.get(i));
        }
    }
}