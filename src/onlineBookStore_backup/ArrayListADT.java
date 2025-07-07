package onlineBookStore_backup;

public class ArrayListADT<E> {
    // Attributes
    private E[] elements;
    private int nextIndex;

    // Constructor
    public ArrayListADT() {
        this.elements = (E[]) new Object[3];
        this.nextIndex = 0;
    }

    public boolean add (E element) {
        if (nextIndex == elements.length) {
            E[] largetElements = (E[]) new Object[elements.length * 2];

            for (int i = 0; i < elements.length; i++) {
                largetElements[i] = elements[i];
            }

            elements = largetElements;
        }

        elements[nextIndex] = element;
        nextIndex++;
        return true;
    }

    public boolean insert (int index, E element) {

        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (nextIndex == elements.length) {
            E[] largerElements = (E[]) new Object[elements.length * 2];

            for ( int i = 0; i < elements.length; i++ ) {
                largerElements[i] = elements[i];
            }

            elements = largerElements;
        }

        // Shift elements to the right
        for (int i = nextIndex; i > index; i--) {
            elements[i] = elements[i-1];
        }

        elements[index] = element;
        nextIndex++;
        return true;
    }

    public E get (int index) {
        if (index < 0 || index  > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    public E set (int index, E element) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    public E remove (int index) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        E oldElement = elements[index];

        // Shift elements to the left
        for (int i = index; i < nextIndex; i++) {
            elements[i] = elements[i + 1];
        }

        elements[nextIndex - 1] = null;
        nextIndex--;


        if (nextIndex < elements.length / 3) {
            E[] smallerElements = (E[]) new Object[elements.length / 2];

            for ( int i = 0; i < nextIndex; i++ ) {
                smallerElements[i] = elements[i];
            }

            elements = smallerElements;
        }

        return oldElement;
    }

    public int size() {
        return nextIndex;
    }

    public int indexOf (E element) {

        for (int i = 0; i < nextIndex; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains (E element) {
        for (int i = 0; i < nextIndex; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmpty() {
        if (nextIndex == 0) {
            return true;
        }

        return false;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for ( int i = 0; i < nextIndex; i++ ) {
            result.append(elements[i]);

            if (i < nextIndex - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
