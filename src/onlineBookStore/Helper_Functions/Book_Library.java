package onlineBookStore.Helper_Functions;

import onlineBookStore.ADT.ArrayListADT;
import onlineBookStore.Algorithm.Sorting;
import onlineBookStore.Model.Book;

public class Book_Library {
    // Attribute
    private final ArrayListADT<Book> library; // Declare a private ArrayListADT to store Book objects in the library

    // Constructor
    public Book_Library() {
        library = new ArrayListADT<>(); // Initialize the books ArrayListADT when a Book_Library object is created

        // Add several book objects to the library
        library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 20.99, 3));
        library.add(new Book("Ulysses", "James Joyce", 25.99, 4));
        library.add(new Book("In Search of Lost Time", "Marcel Proust", 30.99, 5));
        library.add(new Book("1984", "George Orwell", 35.99, 6));
        library.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 40.99, 7));

        Sorting.insertionSortBook(library); // Sort books by title
    }

    public void list_all() {
        // Checks if the book list is empty
        if (library.isEmpty()) {
            System.out.println("No books available");
        } else { // If there are books in the list
            System.out.println("Available books:\n");
            // Loops through each book in sorted list
            for (int i = 0; i < library.size(); i++) {
                Book book = library.get(i); // Gets the current Book object
                System.out.println((i + 1) + ". " + book.toStockString()); // Prints the book's detail along with its stock, and a book number
            }
        }
    }

    // Retrieve a book by its index in the list
    public Book getBook (int index) {
        // Checks if the provided index is within the valid range of the books list
        if (index >= 0 && index < library.size()) {
            return library.get(index); // Returns the Book object at the specified index
        }

        return null; // Returns null if the index is out of bounds
    }

    // To get the total number of books in the library
    public int getLibrarySize() {
        return library.size(); // Returns the number of elements in the books ArrayListADT
    }
}
