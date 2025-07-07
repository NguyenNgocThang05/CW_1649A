package onlineBookStore_backup;

public class Order_List {
    private LinkedQueueADT<Order> order_list; // This queue will store all pending orders
    private LinkedQueueADT<Order> finished_orders; // This queue will store all finished orders

    public Order_List() {
        this.order_list = new LinkedQueueADT<>();
        this.finished_orders = new LinkedQueueADT<>();
    }

    public void addOrder(Order order) {
        this.order_list.offer(order);
        System.out.println("Order " + order.getOrderID() + " added");
    }

   private void displayOrders(LinkedQueueADT<Order> queue) {
        LinkedQueueADT<Order> tempQueue = new LinkedQueueADT<>();
        int originalSize = queue.size();

        if (originalSize == 0) {
            System.out.println("No orders currently.");
            return;
        }

        for (int i = 0; i < originalSize; i++) {
            Order order = queue.poll();
            System.out.println((i + 1) + ". " + order.toString());
            tempQueue.offer(order);
        }

        for (int i = 0; i < originalSize; i++) {
            queue.offer(tempQueue.poll());
        }
   }

   public void listPendingOrders() {
        if (this.order_list.isEmpty()) {
            System.out.println("No orders currently.");
            return;
        }

       System.out.println("\nAll pending orders");
        displayOrders(this.order_list);
       System.out.println("-----------------------------");
   }

   public void finishOrder() {
        if (this.order_list.isEmpty()) {
            System.out.println("No orders to finish.");
            return;
        }

        Order finishedOrder = this.order_list.poll();
        finishedOrder.setStatus("Completed");
        this.finished_orders.offer(finishedOrder);
       System.out.println("Order " + finishedOrder.getOrderID() + " has been finished.");
   }

   public void listFinishedOrders() {
        if (this.finished_orders.isEmpty()) {
            System.out.println("No finished orders currently.");
            return;
        }

       System.out.println("\nAll finished orders:");
        displayOrders(this.finished_orders);
       System.out.println("-----------------------------");
   }

   public void showOrderStatus() {
        if (this.order_list.isEmpty() && this.finished_orders.isEmpty()) {
            System.out.println("No orders to display status for");
            return;
        }

       System.out.println("\nOrder Status:");

       System.out.println("\nPending Orders:");
       if (order_list.isEmpty()) { //
           System.out.println("No pending orders.");
       } else {
           // Re-using the display logic for status view
           LinkedQueueADT<Order> tempPendingQueue = new LinkedQueueADT<>();
           int pendingSize = order_list.size(); //
           for (int i = 0; i < pendingSize; i++) {
               Order order = order_list.poll(); //
               System.out.println("Order ID: " + order.getOrderID() + " | Customer: " + order.getCustomer().getName() + " | Status: " + order.getStatus()); //
               tempPendingQueue.offer(order); //
           }
           for (int i = 0; i < pendingSize; i++) {
               order_list.offer(tempPendingQueue.poll()); //
           }
       }

       System.out.println("\nFinished Orders:");
       if (finished_orders.isEmpty()) { //
           System.out.println("No finished orders.");
       } else {
           // Re-using the display logic for status view
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

   public LinkedQueueADT<Order> getPendingOrderQueue() {
        return this.order_list;
   }

   public LinkedQueueADT<Order> getFinishedOrdersQueue() {
        return this.finished_orders;
   }
}
