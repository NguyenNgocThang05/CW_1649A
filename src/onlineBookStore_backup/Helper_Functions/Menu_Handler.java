package onlineBookStore_backup.Helper_Functions;

import onlineBookStore_backup.ADT.ArrayListADT;
import onlineBookStore_backup.ADT.LinkedStackADT;
import onlineBookStore_backup.Model.Book;
import onlineBookStore_backup.Model.Customer;
import onlineBookStore_backup.Model.Order;
import onlineBookStore_backup.Algorithm.Search;

import java.util.Scanner; // Import Scanner for user input

public class Menu_Handler {
    public void handleOrderBook(Scanner scanner, Book_Library availableBooks, Order_List allOrders) {
        availableBooks.list_all(); // Display available books
        ArrayListADT<Book> booksToOrder = new ArrayListADT<>(); // Create new array list named booksToOrder

        // Check if there are no books in the library
        if (availableBooks.getBookCount() == 0) {
            System.out.println("Cannot place an order as there are no books available.");
            return;
        }

        // Loops until the customer press enter
        while (true) {
            System.out.print("Enter the number of the book you want to order (or press Enter to finish): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            int bookChoice; // Initialize a variable to store the chosen book's number
            try {
                bookChoice = Integer.parseInt(input); // Attempting to convert the input into an integer
            } catch (NumberFormatException e) { // Throws an exception error if the input is not a valid number
                System.out.println("Invalid input. Please enter a number or press Enter to finish.");
                continue;
            }

            // Checks if the book number is out of range
            if (bookChoice < 1 || bookChoice > availableBooks.getBookCount()) {
                System.out.println("Invalid book number. Please try again.");
                continue;
            }

            Book selectedBook = availableBooks.getBook(bookChoice - 1); // Retrieves the selected book from the library
                                                                              // bookChoice - 1 because index starts from 0


            // Check if the selected book is out of stock
            if (selectedBook.getStock() <= 0) {
                System.out.println("Sorry, \"" + selectedBook.getTitle() + "\" is currently out of stock.");
                continue;
            }

            System.out.print("Enter quantity for \"" + selectedBook.getTitle() + "\": ");
            int quantity; // Initialize a variable for quantity
            try {
                quantity = Integer.parseInt(scanner.nextLine()); // Attempting to convert the quantity input to an integer
            } catch (NumberFormatException e) { // Throws an exception error if the quantity input is not a number
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            // Checks if the quantity is smaller or equal to 0
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                continue;
            }

            // Checks if the requested quantity exceeds the available stock
            if (selectedBook.getStock() < quantity) {
                System.out.println("Not enough stock for \"" + selectedBook.getTitle() + "\". Available: " + selectedBook.getStock());
                continue;
            }

            // Create a new Book object to store the selected book object that the user inputted
            Book bookForOrder = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), selectedBook.getPrice(), selectedBook.getStock());
            bookForOrder.setQuantity(quantity); // Set the quantity for the order
            booksToOrder.add(bookForOrder); // Adds the book with its order quantity to the temporary list

            selectedBook.decrementStock(quantity); // Decrements the stock after the order

            System.out.println(quantity + " of \"" + selectedBook.getTitle() + "\" added to your order.");
        }

        // Checks if any book were added into the order after the while loop
        if (booksToOrder.isEmpty()) {
            System.out.println("No books were added to the order.");
            return;
        }

        String customerName;
        // Loops until a customer name is entered
        while (true) {
            System.out.print("Enter customer name: ");
            customerName = scanner.nextLine().trim();
            if (customerName.isEmpty()) {
                System.out.println("Customer name cannot be empty. Please enter a name.");
            } else {
                break;
            }
        }

        String customerAddress;
        // Loops until a customer address is entered
        while (true) {
            System.out.print("Enter customer address: ");
            customerAddress = scanner.nextLine().trim();
            if (customerAddress.isEmpty()) {
                System.out.println("Customer address cannot be empty. Please enter an address.");
            } else {
                break;
            }
        }

        Customer customer = new Customer(customerName, customerAddress); // Create a new Customer object to store customer's info
        Order newOrder = new Order(customer, booksToOrder); // Create a new Order object to store customer's info along the books they ordered
        allOrders.addOrder(newOrder); // Adds the new order to the list of all orders

        System.out.println("\nOrder placed successfully!");
        System.out.println(newOrder);
    }


    public void handleSearchOrderDetail(Scanner scanner, Order_List allOrders, LinkedStackADT<Order> viewedOrderHistory) {

        // Check if there are any orders that had been searched
        if (allOrders.getPendingOrdersQueue().isEmpty() && allOrders.getFinishedOrdersQueue().isEmpty()) {
            System.out.println("No orders to search yet.");
            return;
        }

        System.out.print("Enter Order ID to search: ");
        int searchOrderID; // Initialize a variable to receive the user input

        try {
            searchOrderID = Integer.parseInt(scanner.nextLine()); // Attempting to convert the user input into integer
        } catch (NumberFormatException e) { // Throws an exception error if the ID is not numeric
            System.out.println("Invalid input. Please enter a numeric Order ID.");
            return;
        }

        Order foundOrder = Search.searchOrderById(allOrders, searchOrderID); // Calls the static method from the Search class to find the order

        // Check if an order was found
        if (foundOrder != null) {
            System.out.println("\nOrder Found:");
            System.out.println(foundOrder); // Prints the detail of the found order

            // Checks if the viewedOrderHistory has more than 5 orders
            if (viewedOrderHistory.size() >= 5) {
                LinkedStackADT<Order> temp = new LinkedStackADT<>(); // Create a temporary stack

                // Moves all elements from history stack to the temporary stack
                while (!viewedOrderHistory.isEmpty()) {
                    temp.push(viewedOrderHistory.pop());
                }

                // If the temporary stack is not empty
                if (!temp.isEmpty()) {
                    temp.pop(); // Removes the oldest element from the temporary stack to maintain the max size of 5
                }

                // Moves elements back from the temporary stack to the viewedOrderHistory
                while (!temp.isEmpty()) {
                    viewedOrderHistory.push(temp.pop());
                }
            }

            viewedOrderHistory.push(foundOrder); // Pushes the found order from the recent search onto the viewedOrderHistory stack

            System.out.println("\nOrder added to your search history.");
        } else {
            System.out.println("Order with ID " + searchOrderID + " not found.");
        }
    }


    public void handleDisplayOrderStatus(Order_List allOrders) {
        allOrders.showOrderStatus(); // Display pending and finished orders
    }


    public void handleCompleteOrder(Order_List allOrders) {
        System.out.println("\n--- Complete An Order ---");
        allOrders.finishOrder(); // Finished the order by applying FIFO
    }


    public void handleViewOrderHistory(LinkedStackADT<Order> historyStack) {
        System.out.println("\nRecently Searched Order History");
        // Checks if the history stack is empty
        if (historyStack.isEmpty()) {
            System.out.println("No orders in your search history yet.");
            return;
        }

        ArrayListADT<Order> tempHistoryStack = new ArrayListADT<>(); // Creates a temporary array list to hold orders from the stack

        // Pops all elements from the stack and add them to the array list
        while (!historyStack.isEmpty()) {
            tempHistoryStack.add(historyStack.pop());
        }

        int count = 0; // Initialize a counter for display orders
        // Loops through the array list starting from the end to display the most recent first
        for (int i = tempHistoryStack.size() - 1; i >= 0; i--) {
            if (count == 5) { // Limits the display to 5 most recent searches
                break;
            }
            System.out.println(tempHistoryStack.get(i).toString() + "\n");
            count++; // Increments the counter
        }

        // Pushes all elements back onto history stack to restore its original state
        for (int i = tempHistoryStack.size() - 1; i >= 0; i--) {
            historyStack.push(tempHistoryStack.get(i));
        }

    }
}
