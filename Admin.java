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
        // TODO: implement any Admin-specific initialization
    }

    /**
     * Displays the Admin dashboard.
     * Options: add item, remove item, update item price, view all orders.
     */
    @Override
    public void getDashboard(Scanner scnr) {

        System.out.println("Options: \n ----------");
        System.out.println("Add item: add ");
        System.out.println("Remove item: remove ");
        System.out.println("Update item price: update ");
        System.out.println("View all orders: view ");
        String input = scnr.nextLine().toLowerCase();

        switch(input) {
            case "add":
                System.out.println("Enter the name of the item: ");
                String itemName = scnr.nextLine();
                System.out.println("Enter a price for the item: ");
                int price = scnr.nextInt();
                MenuItem newItem = new MenuItem(itemName, price);
                addMenuItem(newItem);
                break;
            case "remove":
                System.out.println("Enter an item to remove: ");
                removeMenuItem(scnr.nextLine());
                break;
            case "update":
                System.out.println("Enter an item to update: ");
                String itemUpdate = scnr.nextLine();
                System.out.println("Enter a new price for the item: ");
                int newPrice = scnr.nextInt();
                updateMenuItem(itemUpdate, newPrice);
                break;
            case "view":
                viewAllOrders();
                break;
            default:
                System.out.println("Please return a valid operation");
        }
    }

    /**
     * Adds a new MenuItem to the system menu.
     *
     * @param item the MenuItem to add
     */
    public void addMenuItem(MenuItem item) {
        if(globalData.menu.containsKey(item.getItemName())) {
            System.out.println("Item is already on the menu");
        }
        else {
            globalData.menu.put(item.getItemName(), item);
        }
    }

    /**
     * Removes a MenuItem from the system menu by name.
     *
     * @param itemName the name of the item to remove
     */
    public void removeMenuItem(String itemName) {
        if(globalData.menu.containsKey(itemName)) globalData.menu.remove(itemName);
        else System.out.println("Item already does not exist");
    }

    /**
     * Updates the price of an existing MenuItem.
     *
     * @param itemName the name of the item to update
     * @param newPrice the new price to assign
     */
    public void updateMenuItem(String itemName, double newPrice) {
        if(globalData.menu.containsKey(itemName)) {
            globalData.menu.get(itemName).setPrice(newPrice);
        }
        else {
            System.out.println("Item is not on the menu");
        }
    }

    /**
     * Displays all orders currently in the system.
     */
    public void viewAllOrders() {
        // TODO: implement
        System.out.println("List of orders: ");

        for(int i = 0; i < globalData.getOrderQueue().size(); i++) {

        }
    }

    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
