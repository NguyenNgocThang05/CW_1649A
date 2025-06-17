package onlineBookStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize lists for available books and all orders
        CustomArrayList<Book> availableBooks = new CustomArrayList<>();
        CustomArrayList<Order> allOrders = new CustomArrayList<>();

        // Add sample books to the availableBooks list
        availableBooks.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 20.99, 3));
        availableBooks.add(new Book("Ulysses", "James Joyce", 25.99, 4));
        availableBooks.add(new Book("In Search of Lost Time", "Marcel Proust", 30.99, 5));
        availableBooks.add(new Book("1984", "George Orwell", 35.99, 6));
        availableBooks.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 40.99, 7));

        // Start main menu loop
        while (true) {
            // Display menu options
            System.out.println("\nWelcome to Online Bookstore!");
            System.out.println("1. Order Book");
            System.out.println("2. Search order detail");
            System.out.println("3. Display order status");
            System.out.println("4. Complete an order");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            String menuChoice = scanner.nextLine();

            // Handle user menu selection
            switch (menuChoice) {
                case "1":
                    orderBook(scanner, availableBooks, allOrders);
                    break;
                case "2":
                    searchOrder(scanner, allOrders);
                    break;
                case "3":
                    displayOrderStatus(allOrders);
                    break;
                case "4":
                    completeOrder(scanner, allOrders);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return; // Exit program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 to 5.");
            }
        }
    }

    // Method to handle book ordering
    private static void orderBook(Scanner scanner, CustomArrayList<Book> availableBooks, CustomArrayList<Order> allOrders) {
        // Prompt for customer name
        System.out.print("Enter your name (or press enter to cancel): ");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            System.out.println("Order cancelled.");
            return;
        }

        // Prompt for valid address
        String address;
        while (true) {
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
            if (!address.isBlank()) break;
            System.out.println("Address cannot be empty. Try again.");
        }

        Customer newCustomer = new Customer(name, address);

        // Sort books alphabetically before displaying
        Sorting.insertionSort(availableBooks);

        // Show all available books
        System.out.println("\nAvailable books: ");
        for (int i = 0; i < availableBooks.size(); i++) {
            System.out.println((i + 1) + ". " + availableBooks.get(i).toStockString());
        }

        CustomArrayList<Book> selectedBooks = new CustomArrayList<>();

        // Allow the user to pick books in a loop
        while (true) {
            System.out.print("\nEnter the number of the book you'd like to pick (or press Enter to finish): ");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                if (selectedBooks.size() == 0) {
                    System.out.println("No books selected. Order cancelled.");
                } else {
                    // Sort selected books and create the order
                    Sorting.insertionSort(selectedBooks);
                    Order finalOrder = new Order(newCustomer, selectedBooks);
                    allOrders.add(finalOrder);

                    // Display the final order summary
                    System.out.println("\nFinal Order:");
                    System.out.println(finalOrder);
                }
                break;
            }

            try {
                int choice = Integer.parseInt(input);

                if (choice > 0 && choice <= availableBooks.size()) {
                    Book stockBook = availableBooks.get(choice - 1);

                    if (stockBook.getStock() > 0) {
                        stockBook.setStock(stockBook.getStock() - 1);
                        boolean found = false;

                        // If already in selected list, increase quantity
                        for (int i = 0; i < selectedBooks.size(); i++) {
                            Book b = selectedBooks.get(i);
                            if (b.getTitle().equals(stockBook.getTitle())) {
                                b.incrementQuantity();
                                found = true;
                                break;
                            }
                        }

                        // Else add new book to selected list with quantity 1
                        if (!found) {
                            Book orderedBook = new Book(stockBook.getTitle(), stockBook.getAuthor(), stockBook.getPrice(), 0);
                            orderedBook.setQuantity(1);
                            selectedBooks.add(orderedBook);
                        }

                        System.out.println("You picked: " + stockBook.toStockString());
                    } else {
                        System.out.println("Sorry, that book is out of stock.");
                    }
                } else {
                    System.out.println("Invalid choice. Please pick a number between 1 and " + availableBooks.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Method to search and display an order by its ID
    private static void searchOrder(Scanner scanner, CustomArrayList<Order> allOrders) {
        if (allOrders.size() == 0) {
            System.out.println("There are no orders currently");
            return;
        }

        while (true) {
            System.out.print("Enter order ID (or press Enter to cancel): ");
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("Search cancelled");
                break;
            }

            try {
                int targetOrderId = Integer.parseInt(input);
                boolean found = false;

                // Search for the order with matching ID
                for (int i = 0; i < allOrders.size(); i++) {
                    Order order = allOrders.get(i);
                    if (order.getOrderID() == targetOrderId) {
                        System.out.println("Order found:");
                        System.out.println(order);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Order with ID " + targetOrderId + " not found.");
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid order ID number.");
            }
        }
    }

    // Method to display the current status of all orders
    private static void displayOrderStatus(CustomArrayList<Order> allOrders) {
        if (allOrders.size() == 0) {
            System.out.println("No orders placed yet.");
            return;
        }

        System.out.println("\nOrder Status:");
        for (int i = 0; i < allOrders.size(); i++) {
            Order order = allOrders.get(i);
            System.out.println("Order ID: " + order.getOrderID() +
                    ", Customer: " + order.getCustomer().getName() +
                    ", Status: " + order.getStatus());
        }
    }

    // Method to mark an order as completed
    private static void completeOrder(Scanner scanner, CustomArrayList<Order> allOrders) {
        if (allOrders.size() == 0) {
            System.out.println("No orders to complete.");
            return;
        }

        System.out.print("Enter the Order ID to complete: ");
        try {
            int completeOrderId = Integer.parseInt(scanner.nextLine());
            boolean found = false;

            for (int i = 0; i < allOrders.size(); i++) {
                Order order = allOrders.get(i);

                if (order.getOrderID() == completeOrderId) {
                    found = true;
                    if (order.getStatus().equals("Completed")) {
                        System.out.println("The order is already completed.");
                    } else {
                        order.setStatus("Completed");
                        System.out.println("Order ID: " + completeOrderId + " completed");
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("Order ID not found.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number");
        }
    }
}

// Case 4 bug: Fix the bug where the user enter something other than numbers, the program will let the user try again