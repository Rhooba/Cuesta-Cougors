/**
 * Handles the creation of new user accounts in the food delivery system.
 * Validates input, creates the appropriate user object,
 * adds it to GlobalData, and saves the updated user list to file.
 *
 * @author
 * @version 1.0
 */
public class AccountCreation {

    private GlobalData globalData;
    private FileManager fileManager;

    /**
     * Constructs an AccountCreation helper.
     *
     * @param globalData  the central data store
     * @param fileManager the file I/O handler
     */
    public AccountCreation(GlobalData globalData, FileManager fileManager) {
        this.globalData = globalData;
        this.fileManager = fileManager;
    }

    /**
     * Creates a new Customer account.
     * Check that the username is not already taken before creating.
     * Add to GlobalData and save customers.txt on success.
     *
     * @param username        the desired username
     * @param password        the desired password
     * @param name            the customer's full name
     * @param deliveryAddress the customer's delivery address
     * @return the newly created Customer, or null if the username already exists
     */
    public Customer createCustomer(String username, String password, String name, String deliveryAddress) {
        if (usernameExists(username)) {  // reject if someone already has this username
            return null;
        }
        Customer customer = new Customer(username, password, name, deliveryAddress); // build the object
        globalData.addCustomer(customer);       // register in the live system
        fileManager.saveCustomers(globalData);  // write updated list to customers.txt
        return customer;                        // return so the caller can confirm success
    }

    /**
     * Creates a new Driver account.
     * Check that the username is not already taken before creating.
     * Add to GlobalData, add to the driver pool, and save drivers.txt on success.
     *
     * @param username        the desired username
     * @param password        the desired password
     * @param name            the driver's full name
     * @param currentLocation the driver's starting location
     * @return the newly created Driver, or null if the username already exists
     */
    public Driver createDriver(String username, String password, String name, String currentLocation) {
        if (usernameExists(username)) {  // reject if username is taken
            return null;
        }
        Driver driver = new Driver(username, password, name, currentLocation); // build the object
        globalData.addDriver(driver);        // addDriver internally calls addDriverToPool if available
        globalData.addDriverToPool(driver);  // also explicitly add to pool so they can receive orders right away
        fileManager.saveDrivers(globalData); // persist updated driver list to drivers.txt
        return driver;
    }

    /**
     * Creates a new Admin account.
     * Check that the username is not already taken before creating.
     * Add to GlobalData and save admins.txt on success.
     *
     * @param username the desired username
     * @param password the desired password
     * @param name     the admin's full name
     * @return the newly created Admin, or null if the username already exists
     */
    public Admin createAdmin(String username, String password, String name) {
        if (usernameExists(username)) {  // reject if username is taken
            return null;
        }
        Admin admin = new Admin(username, password, name); // build the object
        globalData.addAdmin(admin);          // register in the live system
        fileManager.saveAdmins(globalData);  // persist updated admin list to admins.txt
        return admin;
    }

    /**
     * Checks whether a given username is already taken across all user lists.
     *
     * @param username the username to check
     * @return true if already in use, false if available
     */
    public boolean usernameExists(String username) {
        // search every customer — usernames must be unique across ALL user types, not just within one group
        for (Customer customer : globalData.getCustomers()) {
            if (customer.getUsername().equals(username)) {
                return true; // found a match, stop searching
            }
        }
        // search every driver
        for (Driver driver : globalData.getDrivers()) {
            if (driver.getUsername().equals(username)) {
                return true;
            }
        }
        // search every admin
        for (Admin admin : globalData.getAdmins()) {
            if (admin.getUsername().equals(username)) {
                return true;
            }
        }
        return false; // made it through all lists with no match — username is available
    }

    /**
     * Validates that a password meets minimum requirements.
     * At minimum: not null, at least 6 characters.
     *
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidPassword(String password) {
        // null check first — calling .length() on null would crash with a NullPointerException
        if (password == null || password.length() < 6) {
            return false; // password is missing or too short
        }
        return true; // password passes minimum requirements
    }
}
