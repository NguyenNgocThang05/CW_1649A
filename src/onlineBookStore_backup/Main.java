package onlineBookStore_backup;

import onlineBookStore_backup.ADT.LinkedStackADT;
import onlineBookStore_backup.Helper_Functions.Book_Library;
import onlineBookStore_backup.Helper_Functions.Menu_Handler;
import onlineBookStore_backup.Helper_Functions.Order_List;
import onlineBookStore_backup.Model.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book_Library availableBooks = new Book_Library();
        Order_List allOrders = new Order_List();
        LinkedStackADT<Order> viewedOrderHistory = new LinkedStackADT<>();

        Menu_Handler menuHandler = new Menu_Handler();

        while (true) {
            System.out.println("\nWelcome to Online Bookstore");
            System.out.println("1. Order Book");
            System.out.println("2. Search Order Detail");
            System.out.println("3. Display Order Status");
            System.out.println("4. Complete An Order");
            System.out.println("5. View Search Order History");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String menuChoice = scanner.nextLine();

            // Handle user menu selection
            switch (menuChoice) {
                case "1":
                    menuHandler.handleOrderBook(scanner, availableBooks, allOrders);
                    break;

                case "2":
                    menuHandler.handleSearchOrderDetail(scanner, allOrders, viewedOrderHistory);
                    break;

                case "3":
                    menuHandler.handleDisplayOrderStatus(allOrders);
                    break;

                case "4":
                    menuHandler.handleCompleteOrder(allOrders);
                    break;

                case "5":
                    menuHandler.handleViewOrderHistory(scanner, viewedOrderHistory);
                    break;

                case "6":
                    System.out.println("Exiting..");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 to 5");
            }
        }
    }
}
