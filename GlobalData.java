import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * Central data store for the food delivery system.
 * Holds all active runtime data including the menu, driver pool,
 * order queue, and user lists.
 *
 * Data structures used:
 *   HashMap<String, MenuItem>  — menu storage
 *   PriorityQueue<Driver>      — available driver pool
 *   Queue<Order>               — incoming order processing
 *   ArrayList                  — master lists of all users
 *
 * @author
 * @version 1.0
 */
public class GlobalData {

    private HashMap<String, MenuItem> menu;
    private PriorityQueue<Driver> driverPool;
    private Queue<Order> orderQueue;
    private List<Customer> customers;
    private List<Driver>   drivers;
    private List<Admin>    admins;

    /**
     * Constructs a GlobalData instance and initializes all data structures.
     * Called once at application startup before loading from files.
     */
    public GlobalData() {
        this.menu       = new HashMap<>();
        this.driverPool = new PriorityQueue<>();
        this.orderQueue = new LinkedList<>();
        this.customers  = new ArrayList<>();
        this.drivers    = new ArrayList<>();
        this.admins     = new ArrayList<>();
    }

    // --- Order Queue ---

    /**
     * Adds a new order to the back of the order queue.
     *
     * @param order the Order to enqueue
     */
    public void enqueueOrder(Order order) {
        // TODO: implement
    }

    /**
     * Removes and returns the next order from the front of the queue.
     *
     * @return the next Order, or null if the queue is empty
     */
    public Order dequeueOrder() {
        return null; // TODO: implement
    }

    /**
     * Returns without removing the next order in the queue.
     *
     * @return the next Order, or null if queue is empty
     */
    public Order peekNextOrder() {
        return null; // TODO: implement
    }

    // --- Driver Pool ---

    /**
     * Adds an available driver to the driver pool.
     *
     * @param driver the Driver to add
     */
    public void addDriverToPool(Driver driver) {
        // TODO: implement
    }

    /**
     * Removes and returns the highest-rated available driver from the pool.
     *
     * @return the top Driver, or null if the pool is empty
     */
    public Driver getBestDriver() {
        return null; // TODO: implement
    }

    // --- Order Processing ---

    /**
     * Processes the next order in the queue:
     * dequeues the order, selects the best available driver,
     * assigns the driver, and updates the order status.
     * Print a message if no drivers are currently available.
     */
    public void processNextOrder() {
        // TODO: implement
    }

    // --- Login ---

    /**
     * Searches all user lists for a matching username and validates the password.
     *
     * @param username      the entered username
     * @param inputPassword the entered password
     * @return the matching User, or null if not found or password is wrong
     */
    public User login(String username, String inputPassword) {
        return null; // TODO: implement — search customers, drivers, admins
    }

    // --- Menu ---

    /** @param item the MenuItem to add */
    public void addMenuItem(MenuItem item) {
        // TODO: implement
    }

    /** @param itemName the name of the item to remove */
    public void removeMenuItem(String itemName) {
        // TODO: implement
    }

    /**
     * @param itemName the name of the item to look up
     * @return the matching MenuItem, or null if not found
     */
    public MenuItem getMenuItem(String itemName) {
        return null; // TODO: implement
    }

    /** @return the full menu HashMap */
    public HashMap<String, MenuItem> getMenu() {
        return null; // TODO: implement
    }

    // --- User Lists ---

    /** @param customer Customer to add to master list */
    public void addCustomer(Customer customer) {
        // TODO: implement
    }

    /** @param driver Driver to add to master list */
    public void addDriver(Driver driver) {
        // TODO: implement
    }

    /** @param admin Admin to add to master list */
    public void addAdmin(Admin admin) {
        // TODO: implement
    }

    /** @return list of all Customers */
    public List<Customer> getCustomers() {
        return null; // TODO: implement
    }

    /** @return list of all Drivers */
    public List<Driver> getDrivers() {
        return null; // TODO: implement
    }

    /** @return list of all Admins */
    public List<Admin> getAdmins() {
        return null; // TODO: implement
    }

    /** @return the current order queue */
    public Queue<Order> getOrderQueue() {
        return null; // TODO: implement
    }

    /** @return the current driver pool */
    public PriorityQueue<Driver> getDriverPool() {
        return null; // TODO: implement
    }
}
