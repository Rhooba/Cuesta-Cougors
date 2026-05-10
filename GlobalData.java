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
     */
    public void enqueueOrder(Order order) {
        orderQueue.offer(order);
        
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
        if (driver != null && driver.isAvailable()) {
            driverPool.offer(driver);
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
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }
        
        Driver driver = getBestDriver();
        if (driver == null) {
            System.out.println("No drivers available at the moment. Please wait...");
            return;
        }
        
        Order order = dequeueOrder();
        driver.setAssignedOrder(order);
        driver.setAvailable(false);
        order.setAssignedDriver(driver);
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
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.login(inputPassword)) {
                return customer;
            }
        }

        for (Driver driver : drivers) {
            if(driver.getUsername().equals(username) && driver.login(inputPassword)) {
                return driver;
            }
        }

        for (Admin admin : admins) {
            if(admin.getUsername().equals(username) && admin.login(inputPassword)) {
                return admin;
            }
        }
        return null; // no match found
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
        customer.setGlobalData(this);
    }

    //Ask Brandon to explain this whole globalData reference thing please
    /** @param driver the Driver to add to the master driver list and, if available, the driver pool */
    public void addDriver(Driver driver) {
        drivers.add(driver);
        driver.setGlobalData(this);
        // Add driver to pool since they start as available
        if (driver.isAvailable()) {
            addDriverToPool(driver);
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

    /** @return the current order queue */
    public Queue<Order> getOrderQueue() { return orderQueue; }

    /** @return the priority queue of currently available drivers, ordered by rating */
    public PriorityQueue<Driver> getDriverPool() {
        return driverPool;
    }
}
