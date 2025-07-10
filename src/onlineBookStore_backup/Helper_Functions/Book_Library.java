package onlineBookStore_backup.Helper_Functions;

import onlineBookStore_backup.ADT.ArrayListADT;
import onlineBookStore_backup.Algorithm.Sorting;
import onlineBookStore_backup.Model.Book;

public class Book_Library {
    // Attribute
    private ArrayListADT<Book> books; // Declare a private ArrayListADT to store Book objects in the library

    // Constructor
    public Book_Library() {
        books = new ArrayListADT<>(); // Initialize the books ArrayListADT when a Book_Library object is created

        // Add several book objects to the library
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 20.99, 3));
        books.add(new Book("Ulysses", "James Joyce", 25.99, 4));
        books.add(new Book("In Search of Lost Time", "Marcel Proust", 30.99, 5));
        books.add(new Book("1984", "George Orwell", 35.99, 6));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 40.99, 7));
    }

    public void list_all() {
        // Checks if the book list is empty
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else { // If there are books in the list
            Sorting.insertionSort(books); // Sort the books in the library using the insertion sort

            System.out.println("Available books:\n");
            // Loops through each book in sorted list
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i); // Gets the current Book object
                System.out.println((i + 1) + ". " + book.toStockString()); // Prints the book's detail along with its stock, and a book number
            }
        }
    }

    // Retrieve a book by its index in the list
    public Book getBook (int index) {
        // Checks if the provided index is within the valid range of the books list
        if (index >= 0 && index < books.size()) {
            return books.get(index); // Returns the Book object at the specified index
        }

        return null; // Returns null if the index is out of bounds
    }

    // To get the total number of books in the library
    public int getBookCount() {
        return books.size(); // Returns the number of elements in the books ArrayListADT
    }
}
