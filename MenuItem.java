/**
 * Represents a single item on the food delivery menu.
 * Menu items are loaded from menu.txt at startup and stored in GlobalData.
 *
 * Responsibilities:
 * - Store item name and price
 * - Provide formatted output for display and file storage
 *
 * @author
 * @version 1.1
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
        this.itemName = itemName;
        this.price = price;
    }

    /** @return the name of this menu item */
    public String getItemName() {
        return itemName;
    }

    /** @return the price of this menu item */
    public double getPrice() {
        return price;
    }

    /** @param price the updated price */
    public void setPrice(double price) {
        this.price = price;
    }

    /** @param itemName the updated item name */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Returns a formatted String of this item for display and file writing.
     * Example format: "Hamburger - $5.99"
     */
    @Override
    public String toString() {
        return itemName + " - $" + String.format("%.2f", price);
    }
}
