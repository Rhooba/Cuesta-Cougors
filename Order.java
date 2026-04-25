import java.util.List;

/**
 * Represents a customer's order in the food delivery system.
 * Each new order is appended to orders.txt via FileManager.
 *
 * @author
 * @version 1.0
 */
public class Order {

    private int orderId;
    private Customer customer;
    private List<MenuItem> items;
    private OrderStatus status;
    private double total;
    private Driver assignedDriver;
    private String deliveryAddress;

    /** Auto-increments to give each order a unique ID */
    private static int nextId = 1;

    /**
     * Constructs a new Order for a customer with a list of items.
     * Automatically assigns a unique orderId.
     * Initial status is PLACED.
     *
     * @param customer        the Customer placing the order
     * @param items           the list of MenuItems in this order
     * @param deliveryAddress the delivery address for this order
     */
    public Order(Customer customer, List<MenuItem> items, String deliveryAddress) {
        this.orderId = nextId++;
        this.status  = OrderStatus.PLACED;
        // TODO: implement remaining initialization
        // this.total = calculateTotal();
    }

    /**
     * Calculates and returns the total cost of all items in this order.
     *
     * Time Complexity:  O(n) where n is the number of items
     * Space Complexity: O(1)
     *
     * @return total cost as a double
     */
    public double calculateTotal() {
        return 0.0; // TODO: implement
    }

    /** @return the unique order ID */
    public int getOrderId() {
        return 0; // TODO: implement
    }

    /** @return the Customer who placed this order */
    public Customer getCustomer() {
        return null; // TODO: implement
    }

    /** @return the list of MenuItems in this order */
    public List<MenuItem> getItems() {
        return null; // TODO: implement
    }

    /** @return the current OrderStatus */
    public OrderStatus getStatus() {
        return null; // TODO: implement
    }

    /**
     * Updates the status of this order.
     * Called by Driver when marking in-progress or delivered.
     *
     * @param status the new OrderStatus
     */
    public void setStatus(OrderStatus status) {
        // TODO: implement
    }

    /** @return the calculated order total */
    public double getTotal() {
        return 0.0; // TODO: implement
    }

    /** @return the assigned Driver, or null if not yet assigned */
    public Driver getAssignedDriver() {
        return null; // TODO: implement
    }

    /** @param driver the Driver to assign */
    public void setAssignedDriver(Driver driver) {
        // TODO: implement
    }

    /** @return the delivery address for this order */
    public String getDeliveryAddress() {
        return null; // TODO: implement
    }

    /**
     * Returns a formatted String of this order for display and file logging.
     * Example: "Order #1 | Customer: john | Status: PLACED | Total: $12.97"
     */
    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
