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
    private FileManager fileManager;

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
     */
    public void enqueueOrder(Order order) {
        orderQueue.offer(order); // offer() adds the order to the back of the line
    }

    /**
     * Removes and returns the next order from the front of the queue.
     */
    public Order dequeueOrder() {
        return orderQueue.poll();
    }

    /**
     * Returns without removing the next order in the queue.
     */
    public Order peekNextOrder() {
        return orderQueue.peek();
    }

    // --- Driver Pool ---

    /**
     * Adds an available driver to the driver pool.
     */
    public void addDriverToPool(Driver driver) {
        // only add the driver if they actually exist and are currently free
        if (driver != null && driver.isAvailable()) {
            driverPool.offer(driver); // offer() inserts into the PriorityQueue; position is based on compareTo (rating)
        }
    }

    /**
     * Removes and returns the highest-rated available driver from the pool.
     */
    public Driver getBestDriver() {
        return driverPool.poll();
    }

    /** @return true if no drivers are currently available in the pool */
    public boolean isPoolEmpty() {
        return driverPool.isEmpty();
    }

    /** @return the number of available drivers currently in the pool */
    public int getPoolSize() {
        return driverPool.size();
    }

    /**
     * Removes a specific driver from the pool without polling the head.
     * @param driver the driver to remove
     * @return true if the driver was in the pool and was removed
     */
    public boolean removeDriverFromPool(Driver driver) {
        return driverPool.remove(driver);
    }

    /** @return the highest-rated available driver without removing them from the pool */
    public Driver peekBestDriver() {
        return driverPool.peek();
    }

    // --- Order Processing ---

    /**
     * Processes the next order in the queue:
     * dequeues the order, selects the best available driver,
     * assigns the driver, and updates the order status.
     * Print a message if no drivers are currently available.
     */
    public void processNextOrder() {
        // stop early if there are no orders waiting
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }

        // poll() removes and returns the highest-rated available driver; null if pool is empty
        Driver driver = getBestDriver();
        if (driver == null) {
            System.out.println("No drivers available at the moment. Please wait...");
            return;
        }

        // pull the next order off the front of the queue
        Order order = dequeueOrder();

        // link the driver and order to each other
        driver.setAssignedOrder(order);
        driver.setAvailable(false);       // mark driver as busy so they leave the pool
        order.setAssignedDriver(driver);  // store which driver got this order
        order.setStatus(OrderStatus.ACCEPTED);
        System.out.println("Order # " + order.getOrderId() + " assigned to driver " + driver.getName());
    }

    // --- Login ---

    /**
     * Searches all user lists for a matching username and validates the password.
     * @param username     
     * @param inputPassword 
     * @return the matching User, or null if not found or password is wrong
     */
    public User login(String username, String inputPassword) {
        // check every customer — if username matches and password is correct, return that user
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.login(inputPassword)) {
                return customer;
            }
        }

        // same check for drivers
        for (Driver driver : drivers) {
            if (driver.getUsername().equals(username) && driver.login(inputPassword)) {
                return driver;
            }
        }

        // same check for admins
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.login(inputPassword)) {
                return admin;
            }
        }

        return null; // no match found across any user type
    }
    


    // --- Menu ---

    /** @param item the MenuItem to add */
    public void addMenuItem(MenuItem item) {
        menu.put(item.getItemName(), item);
    }

    /** @param itemName the name of the item to remove */
    public void removeMenuItem(String itemName) {
        menu.remove(itemName);
    }

    /**
     * @param itemName 
     * @return the matching MenuItem, or null if not found
     */
    public MenuItem getMenuItem(String itemName) {
        return menu.get(itemName);
    }

    /** @return the full menu HashMap */
    public HashMap<String, MenuItem> getMenu() {
        return menu;
    }
    // --- User Lists ---

    /** @param customer the Customer to add to the master customer list */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setGlobalData(this);       // give customer access to the live data store
        customer.setFileManager(fileManager); // give customer access to file I/O so it can log orders
    }

    /** @param driver the Driver to add to the master driver list and, if available, the driver pool */
    public void addDriver(Driver driver) {
        drivers.add(driver);                 // add to the permanent master list
        driver.setGlobalData(this);          // give the driver a reference back to GlobalData so they can call addDriverToPool when they finish a delivery
        if (driver.isAvailable()) {
            addDriverToPool(driver);         // if they're free, also put them in the priority queue for order dispatch
        }
    }

    /** @param admin the Admin to add to the master admin list */
    public void addAdmin(Admin admin) {
        admins.add(admin);
        admin.setGlobalData(this);
    }

    /** @return the master list of all registered customers */
    public List<Customer> getCustomers() {
        return customers;
    }

    /** @return the master list of all registered drivers */
    public List<Driver> getDrivers() {
        return drivers;
    }

    /** @return the master list of all registered admins */
    public List<Admin> getAdmins() {
        return admins;
    }

    /** @param fileManager the FileManager instance to use for order persistence */
    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /** @return the current order queue */
    public Queue<Order> getOrderQueue() { return orderQueue; }

    /** @return the priority queue of currently available drivers, ordered by rating */
    public PriorityQueue<Driver> getDriverPool() {
        return driverPool;
    }
}
