package onlineBookStore_backup.Model;

public class Book {
    // Attributes
    private String title;
    private String author;
    private double price;
    private int stock;
    private int quantity;

    // Constructor
    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.quantity = 0;
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

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementStock(int amount) {
        if (this.stock >= amount) {
            this.stock -= amount;
        } else {
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
        return toStockString();
    }
}
