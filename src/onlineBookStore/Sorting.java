package onlineBookStore;

public class Sorting {

    // Insertion sort to sort books by title (A-Z)
    public static void insertionSort(CustomArrayList<Book> bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            Book currentBook = bookList.get(i);
            int j = i - 1;

            // Compare current book's title with the previous books
            while (j >= 0 && bookList.get(j).getTitle().compareToIgnoreCase(currentBook.getTitle()) > 0) {
                // Shift the book to the right
                bookList.set(j + 1, bookList.get(j));
                j--;
            }
            // Place the current book at the correct position
            bookList.set(j + 1, currentBook);
        }
    }
}