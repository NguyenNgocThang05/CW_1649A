package onlineBookStore_backup.Model;

public class Book {
    // Attributes
    private String title; // Declares a private String variable to store the book's title. 'private' means it can only be accessed within this class.
    private String author; // Declares a private String variable to store the book's author
    private double price; // Declares a private double variable to store the book's price
    private int stock; // Declares a private int variable to store the book's stock
    private int quantity; // Declares a private int variable to store the book's quantity

    // Constructor
    public Book(String title, String author, double price, int stock) {
        this.title = title; // Initialize the title attribute
        this.author = author; // Initialize the author attribute
        this.price = price; // Initialize the price attribute
        this.stock = stock; // Initialize the stock attribute
        this.quantity = 0; // Initialize the quantity attribute to 0 by default when a new book is created
    }

    // Setters and Getters
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

//    public void incrementQuantity() {
//        this.quantity++;
//    }

    public void decrementStock(int amount) {
        // Checks if there is enough stock to decrement
        if (this.stock >= amount) {
            this.stock -= amount; // Decreases the stock attribute by the given amount
        } else {
            // If there isn't enough stock
            System.out.println("No more stocks left for this book.");
        }
    }

    public String toStockString() {
        return "[Title: " + this.title + "] [By: " + this.author + "] [Price: $" + this.price + " ]|Stock: " + this.stock;
    }

    public String toQuantityString() {
        return "[Title: " + this.title + "] [By: " + this.author + "] [Price: $" + this.price + " ]|Quantity: " + this.quantity;
    }

    public String toString() {
        return toQuantityString();
    }
}
