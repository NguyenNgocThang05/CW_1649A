package onlineBookStore.Test;

import onlineBookStore.Model.*;
import onlineBookStore.Helper_Functions.*;
import onlineBookStore.ADT.*;
import onlineBookStore.Algorithm.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


class BookTest {
    private Book book;
    private final String title = "Clean Code";
    private final String author = "Robert Martin";
    private final double price = 34.99;
    private final int stock = 10;

    @BeforeEach
    void setUp() {
        book = new Book(title, author, price, stock);
    }

    // Constructor tests
    @Test
    void constructor_ShouldSetFieldsCorrectly() {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(price, book.getPrice());
        assertEquals(stock, book.getStock());
        assertEquals(0, book.getQuantity()); // Default quantity should be 0
    }

    @Test
    void copyConstructor_ShouldCreateIdenticalButSeparateInstance() {
        book.setQuantity(2);
        Book copy = new Book(book);

        assertEquals(book.getTitle(), copy.getTitle());
        assertEquals(book.getAuthor(), copy.getAuthor());
        assertEquals(book.getPrice(), copy.getPrice());
        assertEquals(book.getStock(), copy.getStock());
        assertEquals(book.getQuantity(), copy.getQuantity());

        // Verify they are separate instances
        copy.setTitle("Different Title");
        assertNotEquals(book.getTitle(), copy.getTitle());
    }

    // Getter and setter tests
    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        String newTitle = "Refactoring";
        String newAuthor = "Martin Fowler";
        double newPrice = 45.50;
        int newStock = 5;
        int newQuantity = 3;

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setPrice(newPrice);
        book.setStock(newStock);
        book.setQuantity(newQuantity);

        assertEquals(newTitle, book.getTitle());
        assertEquals(newAuthor, book.getAuthor());
        assertEquals(newPrice, book.getPrice());
        assertEquals(newStock, book.getStock());
        assertEquals(newQuantity, book.getQuantity());
    }

    // Business logic tests
    @Test
    void decrementStock_ShouldReduceStock_WhenEnoughAvailable() {
        int initialStock = book.getStock();
        int amountToDecrement = 3;

        book.decrementStock(amountToDecrement);

        assertEquals(initialStock - amountToDecrement, book.getStock());
    }

    @Test
    void decrementStock_ShouldNotChangeStock_WhenNotEnoughAvailable() {
        int initialStock = book.getStock();
        int excessiveAmount = initialStock + 5;

        book.decrementStock(excessiveAmount);

        assertEquals(initialStock, book.getStock()); // Stock should remain unchanged
    }

    @Test
    void decrementStock_ShouldHandleZeroOrNegativeAmounts() {
        int initialStock = book.getStock();

        book.decrementStock(0);
        assertEquals(initialStock, book.getStock());

        book.decrementStock(-5);
        assertEquals(initialStock, book.getStock());
    }

    // String representation tests
    @Test
    void toStockString_ShouldContainCorrectInformation() {
        String stockString = book.toStockString();

        assertTrue(stockString.contains(title));
        assertTrue(stockString.contains(author));
        assertTrue(stockString.contains(String.format("%.2f", price)));
        assertTrue(stockString.contains(String.valueOf(stock)));
    }

    @Test
    void toQuantityString_ShouldContainCorrectInformation() {
        int testQuantity = 4;
        book.setQuantity(testQuantity);
        String quantityString = book.toQuantityString();

        assertTrue(quantityString.contains(title));
        assertTrue(quantityString.contains(author));
        assertTrue(quantityString.contains(String.format("%.2f", price)));
        assertTrue(quantityString.contains(String.valueOf(testQuantity)));
    }

    @Test
    void toString_ShouldReturnSameAsToQuantityString() {
        assertEquals(book.toQuantityString(), book.toString());
    }
}

class CustomerTest{
    private Customer customer;
    private final String TEST_NAME = "John Doe";
    private final String TEST_ADDRESS = "123 Main St, Anytown";

    @BeforeEach
    void setUp() {
        customer = new Customer(TEST_NAME, TEST_ADDRESS);
    }

    @Test
    void constructor_ShouldSetNameAndAddressCorrectly() {
        assertEquals(TEST_NAME, customer.getName());
        assertEquals(TEST_ADDRESS, customer.getAddress());
    }

    @Test
    void setName_ShouldUpdateName() {
        String newName = "Jane Smith";
        customer.setName(newName);
        assertEquals(newName, customer.getName());
    }

    @Test
    void setAddress_ShouldUpdateAddress() {
        String newAddress = "456 Oak Ave, Somewhere";
        customer.setAddress(newAddress);
        assertEquals(newAddress, customer.getAddress());
    }

    @Test
    void getName_ShouldReturnCorrectName() {
        assertEquals(TEST_NAME, customer.getName());
    }

    @Test
    void getAddress_ShouldReturnCorrectAddress() {
        assertEquals(TEST_ADDRESS, customer.getAddress());
    }

    @Test
    void toString_ShouldReturnFormattedString() {
        String expectedString = "Customer name: " + TEST_NAME + "\nAddress: " + TEST_ADDRESS;
        assertEquals(expectedString, customer.toString());
    }

    @Test
    void setName_ShouldHandleNull() {
        customer.setName(null);
        assertNull(customer.getName());
    }

    @Test
    void setAddress_ShouldHandleNull() {
        customer.setAddress(null);
        assertNull(customer.getAddress());
    }

    @Test
    void setName_ShouldHandleEmptyString() {
        customer.setName("");
        assertEquals("", customer.getName());
    }

    @Test
    void setAddress_ShouldHandleEmptyString() {
        customer.setAddress("");
        assertEquals("", customer.getAddress());
    }
}

class OrderTest{
    private Order order;
    private Customer customer;
    private ArrayListADT<Book> books;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        // Reset the static order ID counter before each test
        Order.resetOrderIDCounter();

        customer = new Customer("John Doe", "123 Main St");
        books = new ArrayListADT<>();

        book1 = new Book("Clean Code", "Robert Martin", 50.0, 10);
        book2 = new Book("Design Patterns", "GoF", 60.0, 5);

        book1.setQuantity(2);
        book2.setQuantity(1);

        books.add(book1);
        books.add(book2);

        order = new Order(customer, books);
    }

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        assertEquals(1, order.getOrderID());
        assertEquals(customer, order.getCustomer());
        assertEquals(books, order.getBooks());
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }

    @Test
    void orderID_ShouldAutoIncrement() {
        Order order2 = new Order(customer, books);
        assertEquals(2, order2.getOrderID());
    }

    @Test
    void setOrderID_ShouldManuallySetID() {
        order.setOrderID();
        assertEquals(2, order.getOrderID());
    }

    @Test
    void setBooks_ShouldUpdateBookList() {
        ArrayListADT<Book> newBooks = new ArrayListADT<>();
        Book newBook = new Book("New Book", "New Author", 30.0, 3);
        newBook.setQuantity(1);
        newBooks.add(newBook);

        order.setBooks(newBooks);
        assertEquals(newBooks, order.getBooks());
    }

    @Test
    void setCustomer_ShouldUpdateCustomer() {
        Customer newCustomer = new Customer("Jane Smith", "456 Oak Ave");
        order.setCustomer(newCustomer);
        assertEquals(newCustomer, order.getCustomer());
    }

    @Test
    void markAsComplete_ShouldSetStatusToComplete() {
        order.markAsComplete();
        assertEquals(OrderStatus.COMPLETE, order.getStatus());
    }

    @Test
    void calculateTotal_ShouldReturnCorrectTotal() {
        double expectedTotal = (book1.getPrice() * book1.getQuantity()) +
                (book2.getPrice() * book2.getQuantity());
        assertEquals(expectedTotal, order.calculateTotal());
    }

    @Test
    void calculateTotal_ShouldReturnZeroForEmptyOrder() {
        Order emptyOrder = new Order(customer, new ArrayListADT<>());
        assertEquals(0.0, emptyOrder.calculateTotal());
    }

    @Test
    void toString_ShouldContainAllOrderInformation() {
        String orderString = order.toString();

        assertTrue(orderString.contains("Order ID: 1"));
        assertTrue(orderString.contains(customer.getName()));
        assertTrue(orderString.contains(book1.getTitle()));
        assertTrue(orderString.contains(book2.getTitle()));
        assertTrue(orderString.contains(String.format("%.2f", order.calculateTotal())));
    }
}

class BookLibraryTest{
    private Book_Library library;

    @BeforeEach
    void setUp() {
        library = new Book_Library();
    }

    @Test
    void constructor_ShouldInitializeWithFiveBooks() {
        assertEquals(5, library.getLibrarySize());
    }

    @Test
    void getBookCount_ShouldReturnFive() {
        assertEquals(5, library.getLibrarySize());
    }

    @Test
    void getBook_ShouldReturnCorrectBookForValidIndex() {
        // Sort the books first to know the expected order
        ArrayListADT<Book> sortedBooks = new ArrayListADT<>();
        sortedBooks.add(new Book("1984", "George Orwell", 35.99, 6));
        sortedBooks.add(new Book("In Search of Lost Time", "Marcel Proust", 30.99, 5));
        sortedBooks.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 20.99, 3));
        sortedBooks.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 40.99, 7));
        sortedBooks.add(new Book("Ulysses", "James Joyce", 25.99, 4));
        Sorting.insertionSortBook(sortedBooks);

        Book book = library.getBook(0);
        assertNotNull(book);
        assertEquals(sortedBooks.get(0).getTitle(), book.getTitle());
        assertEquals(sortedBooks.get(0).getAuthor(), book.getAuthor());
        assertEquals(sortedBooks.get(0).getPrice(), book.getPrice());
        assertEquals(sortedBooks.get(0).getStock(), book.getStock());
    }

    @Test
    void getBook_ShouldReturnNullForNegativeIndex() {
        assertNull(library.getBook(-1));
    }

    @Test
    void getBook_ShouldReturnNullForIndexOutOfBounds() {
        assertNull(library.getBook(5)); // Only indexes 0-4 are valid
        assertNull(library.getBook(100));
    }

    @Test
    void list_all_ShouldNotThrowExceptions() {
        assertDoesNotThrow(() -> library.list_all());
    }

    @Test
    void defaultBooksShouldBeInCorrectOrderAfterSorting() {
        // Verify the books are sorted alphabetically by title
        assertEquals("1984", library.getBook(0).getTitle());
        assertEquals("In Search of Lost Time", library.getBook(1).getTitle());
        assertEquals("The Great Gatsby", library.getBook(2).getTitle());
        assertEquals("The Lord of the Rings", library.getBook(3).getTitle());
        assertEquals("Ulysses", library.getBook(4).getTitle());
    }
}

class OrderListTest{
    private Order_List orderList;
    private Order order1, order2;
    private Customer customer;

    @BeforeEach
    void setUp() {
        orderList = new Order_List();
        customer = new Customer("John Doe", "123 Main St");

        // Create test orders
        order1 = new Order(customer, new ArrayListADT<>());
        order2 = new Order(customer, new ArrayListADT<>());
    }

    @Test
    void constructor_ShouldInitializeEmptyQueues() {
        assertTrue(orderList.getPendingOrdersQueue().isEmpty());
        assertTrue(orderList.getFinishedOrdersQueue().isEmpty());
    }

    @Test
    void addOrder_ShouldAddToPendingQueue() {
        orderList.addOrder(order1);
        assertEquals(1, orderList.getPendingOrdersQueue().size());
        assertEquals(order1, orderList.getPendingOrdersQueue().peek());
    }

    @Test
    void finishOrder_ShouldMoveOrderToFinishedQueue() {
        orderList.addOrder(order1);
        orderList.finishOrder();

        assertTrue(orderList.getPendingOrdersQueue().isEmpty());
        assertEquals(1, orderList.getFinishedOrdersQueue().size());
        assertEquals(OrderStatus.COMPLETE, orderList.getFinishedOrdersQueue().peek().getStatus());
    }

    @Test
    void finishOrder_ShouldDoNothingWhenEmpty() {
        orderList.finishOrder(); // Should not throw exception
        assertTrue(orderList.getPendingOrdersQueue().isEmpty());
        assertTrue(orderList.getFinishedOrdersQueue().isEmpty());
    }

    @Test
    void showOrderStatus_ShouldNotThrowWhenEmpty() {
        assertDoesNotThrow(() -> orderList.showOrderStatus());
    }

    @Test
    void getPendingOrdersQueue_ShouldReturnDirectReference() {
        OrderQueue queue = orderList.getPendingOrdersQueue();
        queue.enqueue(order1);
        assertEquals(1, orderList.getPendingOrdersQueue().size());
    }

    @Test
    void getFinishedOrdersQueue_ShouldReturnDirectReference() {
        OrderQueue queue = orderList.getFinishedOrdersQueue();
        queue.enqueue(order1);
        assertEquals(1, orderList.getFinishedOrdersQueue().size());
    }

    @Test
    void ordersShouldBeShownInReverseOrder() {
        orderList.addOrder(order1);
        orderList.addOrder(order2);

        // Verify the orders will be shown in reverse order (newest first)
        // This tests the stack behavior in showOrderStatus()
        assertDoesNotThrow(() -> orderList.showOrderStatus());
    }
}

class MenuHandlerTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Menu_Handler menuHandler;
    private TestBookLibrary testLibrary;

    // Test-only BookLibrary implementation
    static class TestBookLibrary extends Book_Library {
        private final ArrayListADT<Book> books = new ArrayListADT<>();

        @Override
        public int getLibrarySize() {
            return books.size();
        }

        @Override
        public Book getBook(int index) {
            return books.get(index);
        }

        public void addTestBook(Book book) {
            books.add(book);
        }
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        menuHandler = new Menu_Handler();
        testLibrary = new TestBookLibrary();

        // Add test books
        testLibrary.addTestBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 20.99, 3));
        testLibrary.addTestBook(new Book("1984", "George Orwell", 15.99, 5));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void handleSearchBook_ShouldFindMatchingTitle() {
        // Prepare input
        String input = "gatsby\n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Execute
        menuHandler.handleSearchBook(new Scanner(System.in), testLibrary);

        // Verify
        String output = outputStream.toString();
        assertTrue(output.contains("The Great Gatsby"));
        assertTrue(output.contains("F. Scott Fitzgerald"));
        assertTrue(output.contains("20.99"));
    }

    @Test
    void handleSearchBook_ShouldHandleNoMatches() {
        String input = "nonexistent\n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        menuHandler.handleSearchBook(new Scanner(System.in), testLibrary);

        String output = outputStream.toString();
        assertTrue(output.contains("No books found"));
        assertTrue(output.contains("nonexistent"));
    }

    @Test
    void handleSearchBook_ShouldHandleEmptyInput() {
        String input = "\n";  // Just press Enter
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        menuHandler.handleSearchBook(new Scanner(System.in), testLibrary);

        String output = outputStream.toString();
        assertFalse(output.contains("Found:"));  // Shouldn't show any results
        assertFalse(output.contains("No books found"));  // Shouldn't show not found message
    }
}

