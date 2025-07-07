package onlineBookStore_backup;

public class Book_Library {
    private ArrayListADT<Book> books;

    public Book_Library() {
        books = new ArrayListADT<>();

        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 20.99, 3));
        books.add(new Book("Ulysses", "James Joyce", 25.99, 4));
        books.add(new Book("In Search of Lost Time", "Marcel Proust", 30.99, 5));
        books.add(new Book("1984", "George Orwell", 35.99, 6));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 40.99, 7));
    }

    public void list_all() {
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            System.out.println("Current available books:\n");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println((i + 1) + ". " + book);
            }
        }
    }

    public Book getBook (int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }

        return null;
    }

    public int getBookCount() {
        return books.size();
    }
}
