import java.util.Scanner;
// Lots to be implemented still
/**
 * Represents an Admin user in the food delivery system.
 * Admins can manage the menu and view all orders.
 *
 * @author
 * @version 1.0
 */
public class Admin extends User {
private GlobalData globalData;


    public void setGlobalData(GlobalData globalData) {
        this.globalData = globalData;
    }


    /**
     * Constructs an Admin with the given credentials.
     *
     * @param username the admin's login username
     * @param password the admin's password
     * @param name     the admin's full name
     */
    public Admin(String username, String password, String name) {
        super(username, password, name);
    }

    /**
     * Displays the Admin dashboard.
     * Options: add item, remove item, update item price, view all orders.
     */
    @Override
    public void getDashboard(Scanner scnr) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Update item");
            System.out.println("4. View all orders");
            System.out.println("5. View menu");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            String choice = scnr.nextLine().trim();

            switch(choice) {
                case "1":
                    System.out.println("Enter the name of the item: ");
                    String itemName = scnr.nextLine();
                    System.out.println("Enter a price for the item: ");
                    double price = scnr.nextDouble();
                    scnr.nextLine(); // consume the leftover newline after nextDouble()
                    MenuItem newItem = new MenuItem(itemName, price);
                    addMenuItem(newItem);
                    break;
                case "2":
                    System.out.println("Enter an item to remove: ");
                    removeMenuItem(scnr.nextLine());
                    break;
                case "3":
                    System.out.println("Enter an item to update: ");
                    String itemUpdate = scnr.nextLine();
                    System.out.println("Enter a new price for the item: ");
                    double newPrice = scnr.nextDouble();
                    scnr.nextLine(); // consume the leftover newline after nextDouble()
                    updateMenuItem(itemUpdate, newPrice);
                    break;
                case "4":
                    viewAllOrders();
                    break;
                case "5":
                    System.out.println("\n--- Current Menu ---");
                    for (MenuItem item : globalData.getMenu().values()) {
                        System.out.println(item.toString());
                    }
                    break;
                case "6":
                    System.out.println("Logging out...");
                    return; // exit the method, returning control to the main loop
                default:
                    System.out.println("Please return a valid operation");
            }
        }
    }

    /**
     * Adds a new MenuItem to the system menu.
     *
     * @param item the MenuItem to add
     */
    public void addMenuItem(MenuItem item) {
        // getMenuItem returns null if the item doesn't exist — if it's NOT null, it's already there
        if (globalData.getMenuItem(item.getItemName()) != null) {
            System.out.println("Item is already on the menu");
        } else {
            globalData.addMenuItem(item); // store in the menu HashMap keyed by item name
        }
    }

    /**
     * Removes a MenuItem from the system menu by name.
     *
     * @param itemName the name of the item to remove
     */
    public void removeMenuItem(String itemName) {
        // make sure the item exists before trying to remove it
        if (globalData.getMenuItem(itemName) != null) {
            globalData.removeMenuItem(itemName); // removes from the menu HashMap by name
        } else {
            System.out.println("Item already does not exist");
        }
    }

    /**
     * Updates the price of an existing MenuItem.
     *
     * @param itemName the name of the item to update
     * @param newPrice the new price to assign
     */
    public void updateMenuItem(String itemName, double newPrice) {
        MenuItem item = globalData.getMenuItem(itemName); // look up the item by name
        if (item != null) {
            item.setPrice(newPrice); // directly update the price on the object — no need to re-add it
        } else {
            System.out.println("Item is not on the menu");
        }
    }

    /**
     * Displays all orders currently in the system.
     */
    public void viewAllOrders() {
        System.out.println("List of orders: ");
        // getOrderQueue() returns the live queue — iterating it does NOT remove the orders
        for (Order o : globalData.getOrderQueue()) {
            System.out.println(o.toString()); // prints: Order #ID | Customer | Status | Driver | Total
        }
    }

    @Override
    public String toString() {
        return "Admin: " + getName();
    }
}
