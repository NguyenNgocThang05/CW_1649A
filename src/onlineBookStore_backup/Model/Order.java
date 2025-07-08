package onlineBookStore_backup.Model;

import onlineBookStore_backup.ADT.ArrayListADT;
import onlineBookStore_backup.Algorithm.Sorting;

public class Order {
    // Attributes
    private int orderID;
    private static int nextOrderID = 1;
    private ArrayListADT<Book> books;
    private Customer customer;
    private String status = "Pending..";

    // Constructor
    public Order (Customer customer, ArrayListADT<Book> books) {
        this.orderID = nextOrderID++;
        this.books = books;
        this.customer = customer;
    }

    // Setters and Getters
    public void setOrderID() {
        this.orderID = nextOrderID++;
    }

    public int getOrderID() {
        return this.orderID;
    }

    public void setBooks(ArrayListADT<Book> books) {
        this.books = books;
    }
    public ArrayListADT<Book> getBooks() {
        return this.books;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < books.size(); i++) {
            Book totalBooks = books.get(i);
            total += totalBooks.getPrice() * totalBooks.getQuantity();
        }

        return total;
    }

    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderID).append("\n");
        orderDetails.append(customer).append("\nBooks Ordered:\n");

        Sorting.insertionSort(books);
        for (int i = 0; i < books.size(); i++) {
            orderDetails.append("- ").append(books.get(i)).append("\n");
        }
        orderDetails.append("Total: $").append(String.format("%.2f", calculateTotal()));
        return orderDetails.toString();
    }
}
