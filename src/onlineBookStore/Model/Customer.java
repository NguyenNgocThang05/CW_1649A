package onlineBookStore.Model;

public class Customer {
    // Attributes
    private String name; // Declares a private String variable to store customer's name
    private String address; // Declares a private String variable to store customer's address

    // Constructor
    public Customer(String name, String address) {
        this.name = name; // Initialize the name attribute of Customer object with the provided name
        this.address = address; // Initialize the name attribute of Customer object with the provided address
    }

    // Setters and Getters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public String toString() {
        return "Customer name: " + this.name + "\nAddress: " + this.address;
    }
}

class CustomerTestDrive {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Username", "123 st");
        System.out.println(customer1);
    }
}
