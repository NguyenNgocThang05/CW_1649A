package onlineBookStore_backup.Algorithm;


import onlineBookStore_backup.ADT.ArrayListADT;
import onlineBookStore_backup.Model.Book;

public class Sorting {
    // Insertion sort to sort books by title (A-Z)
    public static void insertionSort (ArrayListADT<Book> books) {
        for (int i = 1; i < books.size(); i++) {
            Book current = books.get(i);
            int j = i - 1;

            while (j >= 0 && books.get(j).getTitle().compareTo(current.getTitle()) > 0) {
                books.set(j + 1, books.get(j));
                j--;
            }

            books.set(j + 1, current);
        }
    }
}
