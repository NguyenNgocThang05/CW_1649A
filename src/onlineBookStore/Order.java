package onlineBookStore;

public class Order {
    // Attributes
    private int orderID;
    private static int nextOrderID = 1;
    private CustomArrayList<Book> books;
    private Customer customer;
    private String status = "Processing..";

    // Constructor
    public Order(Customer customer, CustomArrayList<Book> books) {
        setOrderID();
        setBooks(books);
        setCustomer(customer);
    }

    // Methods
    public void setOrderID() {
        this.orderID = nextOrderID++; // auto-increment ID
    }

    public int getOrderID() {
        return  orderID;
    }

    public void setBooks(CustomArrayList<Book> books) {
        this.books = books;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            total += book.getPrice() * book.getQuantity();
        }
        return total;
    }

    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderID).append("\n");
        orderDetails.append(customer).append("\nBooks Ordered:\n");

        for (int i = 0; i < books.size(); i++) {
            orderDetails.append("- ").append(books.get(i)).append("\n");
        }
        orderDetails.append("Total: $").append(String.format("%.2f", calculateTotal()));
        return orderDetails.toString();
    }
}