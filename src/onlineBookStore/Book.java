package onlineBookStore;

public class Book {
    // Attributes
    private String title;
    private String author;
    private double price;
    private int stock;
    private int quantity;

    // Constructor
    public Book(String title, String author, double price, int stock) {
        setTitle(title);
        setAuthor(author);
        setPrice(price);
        setStock(stock);
        this.quantity = 0;
    }

    // Methods
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    // Increase the quantity of the book if selected more than once
    public void incrementQuantity() {
        this.quantity++;
    }

    // Display for bookstore stock list
    public String toStockString() {
        return "[Title: " + title + "] [By: " + author + "] [Price: $" + price + " ]|Stock: " + stock;
    }

    // Display for order details
    public String toQuantityString() {
        return "[Title: " + title + "] [By: " + author + "] [Price: $" + price + " ]|Quantity: " + quantity;
    }

    // Default toString override
    @Override
    public String toString() {
        return toQuantityString();
    }
}