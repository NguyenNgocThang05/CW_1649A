package onlineBookStore.Model;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Algorithm.Sorting;

public class Order {
    // Attributes
    private int orderID; // Declares a private int variable to store the ID of the order
    private static int nextOrderID = 1; // Declares a private static int variable to generate order ID. 'static' means it belongs to the class itself, not individual objects
    private ArrayListADT<Book> books; // Declares a private ArrayListADT to hold the Book objects included in this order
    private Customer customer; // Declares a private Customer object to store information of the customer who placed this order
    private OrderStatus status = OrderStatus.PENDING; // Declares a variable to store the status of the order
                                         // "Pending.." as a default value

    // Constructor
    public Order (Customer customer, ArrayListADT<Book> books) {
        this.orderID = nextOrderID++; // Assigns the current value of nextOrderID
        this.books = books; // Initialize the books attribute with the ArrayListADT of books provided
        this.customer = customer; // Initialize the customer attribute with the Customer object provided
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

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void markAsComplete() {
        this.status = OrderStatus.COMPLETE;
    }

    public double calculateTotal() {
        double total = 0.0; // Initialize a double variable to store the total
        // Loops through each book in the books ArrayListADT
        for (int i = 0; i < books.size(); i++) {
            Book totalBooks = books.get(i); // Gets the current Book object from the list
            total += totalBooks.getPrice() * totalBooks.getQuantity(); // Calculates the total price
        }

        return total; // Return the calculated total price
    }

    public String toString() {
        StringBuilder orderDetails = new StringBuilder(); // Creates a string builder called orderDetails
        orderDetails.append("Order ID: ").append(orderID).append("\n"); // Appends the order ID and a new line
        orderDetails.append(customer).append("\nBook(s) Ordered:\n"); // Appends the customer's toString() and "Books Ordered:"

        Sorting.insertionSortBook(books); // Sorts the books in the arraylist using insertion sort
        // Loops through the sorted list of books
        for (int i = 0; i < books.size(); i++) {
            orderDetails.append("- ").append(books.get(i)).append("\n"); // Appends each book's toString()
        }
        orderDetails.append("Total: $").append(String.format("%.2f", calculateTotal())); // Appends the calculated total, formatted to two decimal places
        return orderDetails.toString(); // Returns the final string built by the StringBuilder
    }
}

class OrderTestDrive{
    public static void main(String[] args) {
        Book book1 = new Book("Book 1", "Author 1", 10.0, 5);
        Book book2 = new Book("Book 2", "Author 2", 20.0, 3);

        book1.setQuantity(2);
        book2.setQuantity(1);

        ArrayListADT<Book> books = new ArrayListADT<>();
        books.add(book1);
        books.add(book2);

        Customer customer = new Customer("Test", "Test Address");

        Order order = new Order(customer, books);

        System.out.println(order);
    }
}
