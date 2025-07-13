package onlineBookStore_backup.Helper_Functions;

import onlineBookStore_backup.ADT.LinkedQueueADT;
import onlineBookStore_backup.Model.Order;

public class Order_List {
    // Attributes
    private LinkedQueueADT<Order> pending_orders; // This queue will store all pending orders
    private LinkedQueueADT<Order> finished_orders; // This queue will store all finished orders

    // Constructor
    public Order_List() {
        this.pending_orders = new LinkedQueueADT<>(); // Initialize the pending_orders
        this.finished_orders = new LinkedQueueADT<>(); // Initialize the finished_orders
    }

    // Method to add a new order to the pending list
    public void addOrder(Order order) {
        this.pending_orders.offer(order); // Adds the given Order object to the end of the pending_orders queue
    }

    // Method to mark the oldest pending order as finished
   public void finishOrder() {
        // Checks if there are any pending orders
        if (this.pending_orders.isEmpty()) {
            System.out.println("No orders to finish.");
            return; // Exits the method
        }

        Order finishedOrder = this.pending_orders.poll(); // Removes and return the order at the front of the pending_orders (the oldest pending order)
        finishedOrder.setStatus("Completed"); // Sets the status of the retrieved order to 'completed'
        this.finished_orders.offer(finishedOrder); // Adds the completed order to the finished_orders queue
       System.out.println("Order " + finishedOrder.getOrderID() + " has been finished.");
   }

   // Method to display the status of pending and finished orders
   public void showOrderStatus() {
        // Checks if both pending and finished orders are empty
        if (this.pending_orders.isEmpty() && this.finished_orders.isEmpty()) {
            System.out.println("No orders to display status for");
            return; // Exits the method
        }

       System.out.println("\nOrder Status:");

       System.out.println("\nPending Orders:");
       if (pending_orders.isEmpty()) {
           // Checks if the pending orders queue is empty
           System.out.println("No pending orders.");
       } else {
           // Temporary moves all pending orders to a temporary queue, prints their status,
           // and then move them back to the original pending queue to maintain order
           LinkedQueueADT<Order> tempPendingQueue = new LinkedQueueADT<>(); // Create a temporary queue for pending orders
           int pendingSize = pending_orders.size(); // Stores the current size of the pending orders queue
           // Loops through each order in the pending queue
           for (int i = 0; i < pendingSize; i++) {
               Order order = pending_orders.poll(); // Removes and retrieves the order from the front of the pending queue
               System.out.println("Order ID: " + order.getOrderID() + " | Customer: " + order.getCustomer().getName() + " | Status: " + order.getStatus()); //
               tempPendingQueue.offer(order); // Adds the order to the temporary queue
           }
           // Loops to move orders back from the temporary queue to the original pending queue
           for (int i = 0; i < pendingSize; i++) {
               pending_orders.offer(tempPendingQueue.poll()); // Adds the order back to the original pending queue
           }
       }

       System.out.println("\nFinished Orders:");
       // Checks if the finished order queue is empty
       if (finished_orders.isEmpty()) {
           System.out.println("No finished orders.");
       } else {
           // Similar to pending orders logic
           LinkedQueueADT<Order> tempFinishedQueue = new LinkedQueueADT<>();
           int finishedSize = finished_orders.size(); //
           for (int i = 0; i < finishedSize; i++) {
               Order order = finished_orders.poll(); //
               System.out.println("Order ID: " + order.getOrderID() + " | Customer: " + order.getCustomer().getName() + " | Status: " + order.getStatus()); //
               tempFinishedQueue.offer(order); //
           }
           for (int i = 0; i < finishedSize; i++) {
               finished_orders.offer(tempFinishedQueue.poll()); //
           }
       }
       System.out.println("--------------------------");
   }

   // Method to get the pending orders queue
   public LinkedQueueADT<Order> getPendingOrdersQueue() {
        return this.pending_orders; // Returns the pending orders queue
   }

   // Method to get the finished orders queue
   public LinkedQueueADT<Order> getFinishedOrdersQueue() {
        return this.finished_orders; // Returns the finished orders queue
   }
}
