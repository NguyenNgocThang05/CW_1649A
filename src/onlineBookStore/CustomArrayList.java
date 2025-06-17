package onlineBookStore;

public class CustomArrayList<E> {
    // Attributes
    private E[] elements;     // array to store elements
    private int nextIndex;

    // Constructor
    public CustomArrayList() {
        this.elements = (E[]) new Object[5]; // default array storage
        this.nextIndex = 0;             // number of data has stored in the index
    }

    // Methods
    // Method 1: Add element at the end
    public void add(E element) {
        if (nextIndex == elements.length) {
            resize();
        }
        elements[nextIndex] = element;
        nextIndex++;
    }

    // Method 2: Get element at index
    public E get(int index) {
        if (index < 0 || index >= nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    // Method 3: Remove element at index
    public void remove(int index) {
        if (index < 0 || index >= nextIndex) {
            System.out.println("Index out of bounds");
            return;
        }
        for (int i = index; i < nextIndex - 1; i++) {
            elements[i] = elements[i + 1]; // shift elements to left
        }
        nextIndex--;
    }

    // Method 4: Get current size
    public int size() {
        return nextIndex;
    }

    // Method 5: Resize the array when full
    private void resize() {
        E[] newData = (E[]) new Object[elements.length * 2]; //double the size
        for (int i = 0; i < elements.length; i++) {
            newData[i] = elements[i]; //copy the old data to the new resized data
        }
        elements = newData;
    }

    // Method 6: Set element at index
    public void set(int index, E element) {
        if (index < 0 || index >= nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        elements[index] = element;
    }
}
