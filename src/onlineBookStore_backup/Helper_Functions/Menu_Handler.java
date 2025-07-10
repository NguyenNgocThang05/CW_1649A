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
        availableBooks.list_all();

        if (availableBooks.getBookCount() == 0) {
            System.out.println("Cannot place an order as there are no books available.");
            return;
        }

        ArrayListADT<Book> booksToOrder = new ArrayListADT<>();
        boolean orderingMore = true;

        while (orderingMore) {
            System.out.print("Enter the number of the book you want to order (or press Enter to finish): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                orderingMore = false;
                break;
            }

            int bookChoice = -1;
            try {
                bookChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or press Enter to finish.");
                continue;
            }

            if (bookChoice < 1 || bookChoice > availableBooks.getBookCount()) {
                System.out.println("Invalid book number. Please try again.");
                continue;
            }

            Book selectedBook = availableBooks.getBook(bookChoice - 1);

            if (selectedBook == null) {
                System.out.println("Book not found. Please try again.");
                continue;
            }

            if (selectedBook.getStock() <= 0) {
                System.out.println("Sorry, \"" + selectedBook.getTitle() + "\" is currently out of stock.");
                continue;
            }

            System.out.print("Enter quantity for \"" + selectedBook.getTitle() + "\": ");
            int quantity = -1;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than 0.");
                continue;
            }

            if (selectedBook.getStock() < quantity) {
                System.out.println("Not enough stock for \"" + selectedBook.getTitle() + "\". Available: " + selectedBook.getStock());
                continue;
            }

            Book bookForOrder = new Book(selectedBook.getTitle(), selectedBook.getAuthor(), selectedBook.getPrice(), selectedBook.getStock());
            bookForOrder.setQuantity(quantity);
            booksToOrder.add(bookForOrder);

            selectedBook.decrementStock(quantity);

            System.out.println(quantity + " of \"" + selectedBook.getTitle() + "\" added to your cart.");
        }

        if (booksToOrder.isEmpty()) {
            System.out.println("No books were added to the order.");
            return;
        }

        String customerName;
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
        while (true) {
            System.out.print("Enter customer address: ");
            customerAddress = scanner.nextLine().trim();
            if (customerAddress.isEmpty()) {
                System.out.println("Customer address cannot be empty. Please enter an address.");
            } else {
                break;
            }
        }

        Customer customer = new Customer(customerName, customerAddress);
        Order newOrder = new Order(customer, booksToOrder);
        allOrders.addOrder(newOrder);

        System.out.println("\nOrder placed successfully!");
        System.out.println(newOrder);
    }


    public void handleSearchOrderDetail(Scanner scanner, Order_List allOrders, LinkedStackADT<Order> viewedOrderHistory) {

        // Check if there are any orders in the system
        if (allOrders.getPendingOrdersQueue().isEmpty() && allOrders.getFinishedOrdersQueue().isEmpty()) {
            System.out.println("No orders to search yet.");
            return;
        }

        System.out.print("Enter Order ID to search: ");
        int searchOrderID = -1;

        try {
            searchOrderID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric Order ID.");
            return;
        }

        Order foundOrder = Search.searchOrderById(allOrders, searchOrderID);

        if (foundOrder != null) {
            System.out.println("\nOrder Found:");
            System.out.println(foundOrder.toString());

            if (viewedOrderHistory.size() >= 5) {
                LinkedStackADT<Order> temp = new LinkedStackADT<>();

                while (!viewedOrderHistory.isEmpty()) {
                    temp.push(viewedOrderHistory.pop());
                }

                if (!temp.isEmpty()) {
                    temp.pop();
                }

                while (!temp.isEmpty()) {
                    viewedOrderHistory.push(temp.pop());
                }
            }

            viewedOrderHistory.push(foundOrder);

            System.out.println("\nOrder added to your search history.");
        } else {
            System.out.println("Order with ID " + searchOrderID + " not found.");
        }
    }


    public void handleDisplayOrderStatus(Order_List allOrders) {
        allOrders.showOrderStatus();
    }


    public void handleCompleteOrder(Order_List allOrders) {
        System.out.println("\n--- Complete An Order ---");
        allOrders.finishOrder();
    }


    public void handleViewOrderHistory(Scanner scanner, LinkedStackADT<Order> historyStack) {
        System.out.println("\n--- Recently Searched Order History ---");
        if (historyStack.isEmpty()) {
            System.out.println("No orders in your search history yet.");
            return;
        }

        ArrayListADT<Order> orderList = new ArrayListADT<>();

        while (!historyStack.isEmpty()) {
            orderList.add(historyStack.pop());
        }

        int count = 0;
        for (int i = orderList.size() - 1; i >= 0; i--) {
            if (count == 5) {
                break;
            }
            System.out.println(orderList.get(i).toString() + "\n");
            count++;
        }

        for (int i = orderList.size() - 1; i >= 0; i--) {
            historyStack.push(orderList.get(i));
        }

    }
}
