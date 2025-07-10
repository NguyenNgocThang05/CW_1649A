package onlineBookStore_backup.ADT;

public class ArrayListADT<E> {
    // Attributes
    private E[] elements; // Declares a private array to store the elements of the list
    private int nextIndex; // Declares a private int to keep track of the next available position to add an element, effectively the current size

    // Constructor
    public ArrayListADT() {
        this.elements = (E[]) new Object[3]; // Initialize the elements array with a default capacity of 3
        this.nextIndex = 0; // Initialize nextIndex to 0, meaning that the list is empty
    }

    // Method to add an element at the end of the list
    public boolean add (E element) {
        // Checks if the array is full (nextIndex reached its full capacity)
        if (nextIndex == elements.length) {
            E[] largetElements = (E[]) new Object[elements.length * 2]; // Creates a larger array with double the current capacity

            // Copies all existing element from the old array to the new array
            for (int i = 0; i < elements.length; i++) {
                largetElements[i] = elements[i];
            }

            elements = largetElements; // Replaces the old array with the new array
        }

        elements[nextIndex] = element; // Adds the new element at the nextIndex position
        nextIndex++; // increments nextIndex to point the to the next available spot
        return true; // Returns true after the elements was added
    }

    // Method to insert an element at a specific index
    public boolean insert (int index, E element) {
        // Checks if the provided index is out of bounds
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        // Checks if the array is full, similar to the add method
        if (nextIndex == elements.length) {
            E[] largerElements = (E[]) new Object[elements.length * 2];

            for ( int i = 0; i < elements.length; i++ ) {
                largerElements[i] = elements[i];
            }

            elements = largerElements;
        }

        // Shift elements to the right to make space for the new element
        // Loops from the last element down to the insertion point
        for (int i = nextIndex; i > index; i--) {
            elements[i] = elements[i-1]; // Moves each element one position to the right
        }

        elements[index] = element; // Inserts the new element at the specified index
        nextIndex++; // Increments nextIndex to reflect the new size
        return true; // Returns true after insertion
    }

    // Method to retrieve an element at a specific index
    public E get (int index) {
        // Checks if the provided index is out of bounds
        if (index < 0 || index  > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index]; // Returns the element at the specified index
    }

    // Method to replace an element at a specified index with a new element
    public E set (int index, E element) {
        // Checks if the provided index is out of bounds
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E oldElement = elements[index]; // Stores the element that is being replaced
        elements[index] = element; // Replaces the element at 'index' with the new element
        return oldElement; // Returns the old element
    }

    // Method to remove an element at a specified index
    public E remove (int index) {
        // Checks if the provided index is out of bounds
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E oldElement = elements[index]; // Stores the element that is being removed

        // Shift elements to the left to fill the gap
        // Loops from the removed index up to the second to last element
        for (int i = index; i < nextIndex; i++) {
            elements[i] = elements[i + 1]; // Moves each element one position to the left
        }

        elements[nextIndex - 1] = null; // Sets the last element to null to allow garbage collection
        nextIndex--; // Decrements nextIndex to reflect the new size

        // Checks if the array needs to be shrink,
        // to prevent excessive memory usage
        // Checks if the number of elements is less than one-third of the array's capacity
        if (nextIndex < elements.length / 3) {
            E[] smallerElements = (E[]) new Object[elements.length / 2]; // Creates a smaller array with half the current capacity

            // Copies existing elements to the smaller array
            for ( int i = 0; i < nextIndex; i++ ) {
                smallerElements[i] = elements[i];
            }

            elements = smallerElements; // Replaces the larger array with the smaller array
        }

        return oldElement; // Returns the element that was removed
    }

    // Method to get the current number of elements in the list
    public int size() {
        return nextIndex; // Returns the value of nextIndex. which represents the current size
    }

    // Method to find the first occurrence of a specified element
    public int indexOf (E element) {

        // Loops through each element in the list
        for (int i = 0; i < nextIndex; i++) {
            // Compares the current element with the target element
            if (elements[i].equals(element)) {
                return i; // Returns the index if a match is found
            }
        }

        return -1; // Returns -1 if the element is not found in the list
    }

    // Method to check if the list contains a specific element
    public boolean contains (E element) {
        // Loops through each element in the list
        for (int i = 0; i < nextIndex; i++) {
            // Compares the current element with the target element
            if (elements[i].equals(element)) {
                return true; // Returns true if a match is found
            }
        }

        return false; // Returns false if the element is not found
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        // Checks if nextIndex is 0, meaning no elements inside a list
        if (nextIndex == 0) {
            return true; // Returns true if the list is empty
        }

        return false; // Returns false if the list is not empty
    }

    public String toString() {
        StringBuilder result = new StringBuilder(); // Create a StringBuilder named result
        result.append("["); // Appends an opening brackets to the string
        // Loops through each element in the list
        for ( int i = 0; i < nextIndex; i++ ) {
            result.append(elements[i]); // Appends the string representation of the current element

            // Checks if it's not the last element
            if (i < nextIndex - 1) {
                result.append(", "); // Appends a comma and space if there are more elements
            }
        }
        result.append("]"); // Appends a closing brackets
        return result.toString(); // Returns the final string
    }
}
