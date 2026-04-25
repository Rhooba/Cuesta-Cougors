import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Customer user in the food delivery system.
 * Customers can place orders, view their order history, and rate drivers.
 *
 * @author
 * @version 1.0
 */
public class Customer extends User {

    private List<Order> orderHistory;
    private String deliveryAddress;

    /**
     * Constructs a Customer with the given credentials and delivery address.
     *
     * @param username        the customer's login username
     * @param password        the customer's password
     * @param name            the customer's full name
     * @param deliveryAddress the customer's default delivery address
     */
    public Customer(String username, String password, String name, String deliveryAddress) {
        super(username, password, name);
        this.orderHistory = new ArrayList<>();
        // TODO: implement remaining initialization
    }

    /**
     * Displays the Customer dashboard.
     * Options: place order, view order history, rate a driver.
     */
    @Override
    public void getDashboard() {
        // TODO: implement
    }

    /**
     * Places a new order with the selected menu items.
     * The order should be added to the order queue in GlobalData
     * and appended to orders.txt via FileManager.
     *
     * @param items the list of MenuItems the customer is ordering
     * @return the newly created Order object
     */
    public Order placeOrder(List<MenuItem> items) {
        return null; // TODO: implement
    }

    /**
     * Displays all past orders made by this customer.
     */
    public void viewOrderHistory() {
        // TODO: implement
    }

    /**
     * Adds a completed order to this customer's order history.
     *
     * @param order the Order to record
     */
    public void addOrderToHistory(Order order) {
        // TODO: implement
    }

    /**
     * Submits a rating for a driver after a delivery is completed.
     * Rating must be between 1 and 5 inclusive.
     *
     * @param driver the Driver to rate
     * @param rating the rating score (1–5)
     */
    public void rateDriver(Driver driver, int rating) {
        // TODO: validate rating range, then call driver.addRating()
    }

    /** @return the customer's delivery address */
    public String getDeliveryAddress() {
        return null; // TODO: implement
    }

    /** @param deliveryAddress the new delivery address */
    public void setDeliveryAddress(String deliveryAddress) {
        // TODO: implement
    }

    /** @return the customer's order history */
    public List<Order> getOrderHistory() {
        return null; // TODO: implement
    }

    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
