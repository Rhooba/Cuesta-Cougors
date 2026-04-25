/**
 * Represents a single item on the food delivery menu.
 * Menu items are loaded from menu.txt at startup and stored in GlobalData.
 *
 * @author
 * @version 1.0
 */
public class MenuItem {

    private String itemName;
    private double price;

    /**
     * Constructs a MenuItem with a name and price.
     *
     * @param itemName the display name of the item (e.g. "Hamburger")
     * @param price    the price of the item in dollars
     */
    public MenuItem(String itemName, double price) {
        // TODO: implement
    }

    /** @return the name of this menu item */
    public String getItemName() {
        return null; // TODO: implement
    }

    /** @return the price of this menu item */
    public double getPrice() {
        return 0.0; // TODO: implement
    }

    /** @param price the updated price */
    public void setPrice(double price) {
        // TODO: implement
    }

    /** @param itemName the updated item name */
    public void setItemName(String itemName) {
        // TODO: implement
    }

    /**
     * Returns a formatted String of this item for display and file writing.
     * Example format: "Hamburger - $5.99"
     */
    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
