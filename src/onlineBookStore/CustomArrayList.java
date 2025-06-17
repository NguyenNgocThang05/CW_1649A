package onlineBookStore;

public class CustomArrayList<E> {
    // Attributes
    private Object[] data;     // array to store elements
    private int size;       // size can also mean index

    // Constructor
    public CustomArrayList() {
        data = new Object[5]; // default array storage
        size = 0;             // number of data has stored in the index
    }

    // Methods
    // Method 1: Add element at the end
    public void add(E value) {
        if (size == data.length) {
            resize();
        }
        data[size] = value;
        size++;
    }

    // Method 2: Get element at index
    public E get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return null;
        }
        return (E) data[index];
    }

    // Method 3: Remove element at index
    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1]; // shift elements to left
        }
        size--;
    }

    // Method 4: Get current size
    public int size() {
        return size;
    }

    // Method 5: Resize the array when full
    private void resize() {
        Object[] newData = new Object[data.length * 2]; //double the size
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i]; //copy the old data to the new resized data
        }
        data = newData;
    }

    // Method 6: Set element at index
    public void set(int index, E value) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return;
        }
        data[index] = value;
    }
}
