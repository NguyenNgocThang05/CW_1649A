package onlineBookStore;

public class Customer{
    // Attributes
    private String name;
    private String address;

    // Constructor
    public Customer(String name, String address) {
        setName(name);
        setAddress(address);
    }

    // Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Order by " + name + " from " + address;
    }
}
