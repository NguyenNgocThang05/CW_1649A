package onlineBookStore;

import onlineBookStore.ADT.LinkedStackADT;
import onlineBookStore.Helper_Functions.BookLibrary;
import onlineBookStore.Helper_Functions.MenuManager;
import onlineBookStore.Helper_Functions.OrderList;
import onlineBookStore.Model.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookLibrary availableBooks = new BookLibrary();
        OrderList allOrders = new OrderList();
        LinkedStackADT<Order> orderSearchHistory = new LinkedStackADT<>();

        System.out.println("\nWelcome to Online Bookstore");
        while (true) {
            System.out.println("\n1. Order Book");
            System.out.println("2. Search Order Detail");
            System.out.println("3. Display Order Status");
            System.out.println("4. Complete An Order");
            System.out.println("5. View Order History");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String menuChoice = scanner.nextLine();

            // Handle user menu selection
            switch (menuChoice) {
                case "1":
                    MenuManager.handleOrderBook(scanner, availableBooks, allOrders);
                    break;

                case "2":
                    MenuManager.handleSearchOrderDetail(scanner, allOrders, orderSearchHistory);
                    break;

                case "3":
                    MenuManager.handleDisplayOrderStatus(allOrders);
                    break;

                case "4":
                    MenuManager.handleCompleteOrder(allOrders);
                    break;

                case "5":
                    MenuManager.handleSearchHistory(scanner, orderSearchHistory);
                    break;

                case "6":
                    System.out.println("Exiting..");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 to 6");
            }
        }
    }
}
