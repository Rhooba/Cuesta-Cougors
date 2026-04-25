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
        // TODO: implement
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
    public Customer createCustomer(String username, String password,
                                   String name, String deliveryAddress) {
        return null; // TODO: implement
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
    public Driver createDriver(String username, String password,
                               String name, String currentLocation) {
        return null; // TODO: implement
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
        return null; // TODO: implement
    }

    /**
     * Checks whether a given username is already taken across all user lists.
     *
     * @param username the username to check
     * @return true if already in use, false if available
     */
    public boolean usernameExists(String username) {
        return false; // TODO: implement
    }

    /**
     * Validates that a password meets minimum requirements.
     * At minimum: not null, at least 6 characters.
     *
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidPassword(String password) {
        return false; // TODO: implement
    }
}
