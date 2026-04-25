/**
 * Represents an Admin user in the food delivery system.
 * Admins can manage the menu and view all orders.
 *
 * @author
 * @version 1.0
 */
public class Admin extends User {

    /**
     * Constructs an Admin with the given credentials.
     *
     * @param username the admin's login username
     * @param password the admin's password
     * @param name     the admin's full name
     */
    public Admin(String username, String password, String name) {
        super(username, password, name);
        // TODO: implement any Admin-specific initialization
    }

    /**
     * Displays the Admin dashboard.
     * Options: add item, remove item, update item price, view all orders.
     */
    @Override
    public void getDashboard() {
        // TODO: implement
    }

    /**
     * Adds a new MenuItem to the system menu.
     *
     * @param item the MenuItem to add
     */
    public void addMenuItem(MenuItem item) {
        // TODO: implement
    }

    /**
     * Removes a MenuItem from the system menu by name.
     *
     * @param itemName the name of the item to remove
     */
    public void removeMenuItem(String itemName) {
        // TODO: implement
    }

    /**
     * Updates the price of an existing MenuItem.
     *
     * @param itemName the name of the item to update
     * @param newPrice the new price to assign
     */
    public void updateMenuItem(String itemName, double newPrice) {
        // TODO: implement
    }

    /**
     * Displays all orders currently in the system.
     */
    public void viewAllOrders() {
        // TODO: implement
    }

    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
