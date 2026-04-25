/**
 * Handles all file input and output for the food delivery system.
 * Reads and writes users, menu items, and orders to/from text files.
 *
 * File formats:
 *   menu.txt      — one item per line: "ItemName,price"
 *   customers.txt — one per line: "username,password,name,address"
 *   drivers.txt   — one per line: "username,password,name,location,rating1;rating2;..."
 *   admins.txt    — one per line: "username,password,name"
 *   orders.txt    — append-only log, one order per line
 *
 * Use try-with-resources for all file operations to ensure streams are closed properly.
 *
 * @author
 * @version 1.0
 */
public class FileManager {

    public static final String MENU_FILE      = "menu.txt";
    public static final String CUSTOMERS_FILE = "customers.txt";
    public static final String DRIVERS_FILE   = "drivers.txt";
    public static final String ADMINS_FILE    = "admins.txt";
    public static final String ORDERS_FILE    = "orders.txt";

    /**
     * Reads the menu from menu.txt and populates GlobalData.
     * Split each line by comma to get name and price.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadMenu(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Reads all customers from customers.txt and adds them to GlobalData.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadCustomers(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Reads all drivers from drivers.txt and adds them to GlobalData.
     * Parse ratings by splitting on ";".
     * Add each available driver to the driver pool after loading.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadDrivers(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Reads all admins from admins.txt and adds them to GlobalData.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadAdmins(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Appends a single order to orders.txt.
     * Use append mode so existing entries are not overwritten.
     *
     * @param order the Order to append
     */
    public void appendOrder(Order order) {
        // TODO: implement — use FileWriter with append = true
    }

    /**
     * Writes the current menu to menu.txt, overwriting the file.
     * Called after Admin adds, removes, or updates an item.
     *
     * @param globalData the GlobalData with the current menu
     */
    public void saveMenu(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Writes all customers to customers.txt, overwriting the file.
     *
     * @param globalData the GlobalData with the current customer list
     */
    public void saveCustomers(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Writes all drivers to drivers.txt, overwriting the file.
     * Join each driver's ratings with ";" as a separator.
     *
     * @param globalData the GlobalData with the current driver list
     */
    public void saveDrivers(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Writes all admins to admins.txt, overwriting the file.
     *
     * @param globalData the GlobalData with the current admin list
     */
    public void saveAdmins(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Loads all data into GlobalData at system startup.
     * Call this once from Main before launching the login loop.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadAll(GlobalData globalData) {
        // TODO: call loadMenu, loadCustomers, loadDrivers, loadAdmins in order
    }

    /**
     * Saves all user and menu data back to their files.
     * Call this on system shutdown or after significant data changes.
     *
     * @param globalData the GlobalData instance to persist
     */
    public void saveAll(GlobalData globalData) {
        // TODO: call saveMenu, saveCustomers, saveDrivers, saveAdmins in order
    }
}
