import java.util.List;

/**
 * Represents a customer's order in the food delivery system.
 * Each Order stores its items, total cost, status, assigned driver, and delivery address.
 * Orders are expected to be persisted externally via FileManager.
 *
 * Responsibilities:
 * - Maintain order state (status + driver assignment)
 * - Calculate total cost from MenuItems
 * - Provide formatted output for display/logging
 *
 * @author
 * @version 1.1
 */
public class Order {

    private int orderId;
    private Customer customer;
    private List<MenuItem> items;
    private OrderStatus status;
    private double total;
    private Driver assignedDriver;
    private String deliveryAddress;

    /** Static counter used to generate unique order IDs at runtime */
    private static int nextId = 1;

    /**
     * Constructs a new Order.
     * - Assigns a unique orderId using nextId
     * - Sets initial status to PLACED
     * - Driver is null until assigned
     * - Total is computed immediately from provided items
     *
     * @param customer        the Customer placing the order
     * @param items           list of MenuItems included in the order
     * @param deliveryAddress destination for the order
     */
    public Order(Customer customer, List<MenuItem> items, String deliveryAddress) {
        this.orderId = nextId++; // grab the current ID and immediately increment for the next order
        this.status  = OrderStatus.PLACED; // every new order starts in the PLACED state

        this.customer = customer;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.assignedDriver = null; // no driver yet — assigned later by processNextOrder()

        this.total = calculateTotal(); // compute and cache the total at construction time
    }

    /**
     * Computes the total price by summing all MenuItem prices.
     * Does NOT modify state beyond returning the computed value.
     *
     * Time Complexity:  O(n)
     * Space Complexity: O(1)
     *
     * @return sum of item prices
     */
    public double calculateTotal() {
        double sum = 0.0;
        for (MenuItem item : items) {
            sum += item.getPrice(); // add each item's price to the running total
        }
        return sum;
    }

    /** @return unique identifier for this order */
    public int getOrderId() {
        return orderId;
    }

    /** @return Customer who placed the order */
    public Customer getCustomer() {
        return customer;
    }

    /** @return list of items in this order (direct reference) */
    public List<MenuItem> getItems() {
        return items;
    }

    /** @return current lifecycle state of the order */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Updates the order status (e.g., ASSIGNED, PICKED_UP, DELIVERED).
     * No validation is enforced here—state control is expected externally.
     *
     * @param status new status value
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /** @return cached total calculated at construction */
    public double getTotal() {
        return total;
    }

    /** @return assigned driver, or null if unassigned */
    public Driver getAssignedDriver() {
        return assignedDriver;
    }

    /**
     * Assigns a driver to the order.
     * Automatically transitions status to ASSIGNED if driver is non-null.
     *
     * @param driver Driver responsible for delivery
     */
    public void setAssignedDriver(Driver driver) {
        this.assignedDriver = driver;
        if (driver != null) {
            this.status = OrderStatus.ASSIGNED;
        }
    }

    /** @return delivery destination string */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Returns a concise, formatted summary of the order.
     * Format: Order #ID | Customer | Status | Total
     */
    @Override
    public String toString() {
        return "Order #" + orderId +
               " | Customer: " + customer.getUsername() +
               " | Status: " + status +
               " | Driver: " + (assignedDriver != null ? assignedDriver.getName() : "Unassigned") +
               " | Total: $" + String.format("%.2f", total);
    }
}
