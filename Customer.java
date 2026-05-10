import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Customer user in the food delivery system.
 * Customers can place orders, view their order history, and rate drivers.
 *
 * @author
 * @version 1.0
 */
public class Customer extends User {

    private List<Order> orderHistory;
    private String deliveryAddress;
    private GlobalData globalData;

    /**
     * Constructs a Customer with the given credentials and delivery address.
     *
     * @param username        the customer's login username
     * @param password        the customer's password
     * @param name            the customer's full name
     * @param deliveryAddress the customer's default delivery address
     */
    public Customer(String username, String password, String name, String deliveryAddress) {
        super(username, password, name);
        this.orderHistory = new ArrayList<>();
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Displays the Customer dashboard.
     * Options: place order, view order history, rate a driver.
     *
     * @param scnr the shared Scanner for reading user input
     */
    @Override
    public void getDashboard(Scanner scnr) {
        while (true) {
            System.out.println("\n--- Customer Dashboard ---");
            System.out.println("1. Place Order");
            System.out.println("2. View Order History");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            String choice = scnr.nextLine().trim();

            switch (choice) {
                case "1":
                    placeOrder(scnr, null);
                    break;
                case "2":
                    viewOrderHistory();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Places a new order with the selected menu items.
     * The order should be added to the order queue in GlobalData
     * and appended to orders.txt via FileManager.
     *
     * @param items the list of MenuItems the customer is ordering
     * @return the newly created Order object
     */
    public Order placeOrder(Scanner scanner, List<MenuItem> items) {
        // convert the menu HashMap values into an indexed list so we can display by number
        List<MenuItem> menuList = new ArrayList<>(globalData.getMenu().values());

        System.out.println("\n--- Menu ---");

        // print each item with a number so the customer can pick by index
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println((i + 1) + ". " + menuList.get(i).toString());
        }

        List<MenuItem> selectedItems = new ArrayList<>(); // holds the items the customer picks

        System.out.println("Enter item number to add, or type done to finish: ");
        String input = scanner.nextLine().trim();

        // keep adding items until the customer types "done"
        while (!input.equals("done")) {
            int choice = Integer.parseInt(input) - 1; // convert "1" to index 0, "2" to index 1, etc.

            if (choice >= 0 && choice < menuList.size()) { // make sure the number is valid
                MenuItem selected = menuList.get(choice);  // look up the item by index
                selectedItems.add(selected);
                System.out.println(selected.getItemName() + " added.");
            } else {
                System.out.println("Invalid Selection.");
            }

            System.out.println("Add another item or type done to finish: ");
            input = scanner.nextLine().trim();
        }

        System.out.println("\n--- Order Summary ---");

        for (MenuItem item : selectedItems) {
            System.out.println(item.toString());
        }

        // create the order with this customer, their selected items, and their saved address
        Order order = new Order(this, selectedItems, deliveryAddress);
        orderHistory.add(order);          // save to this customer's history
        globalData.enqueueOrder(order);   // add to the system queue for processing
        System.out.println("Your order has been placed. Order Total: $" + order.getTotal());
        return order;
    }

    /**
     * Displays all past orders made by this customer.
     */
    public void viewOrderHistory() {
        // view order history
        System.out.println("Your Order History: ");
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }

    /**
     * Adds a completed order to this customer's order history.
     *
     * @param order the Order to record
     */
    public void addOrderToHistory(Order order) {
        //  add to order history
        orderHistory.add(order);
    }

    /**
     * Submits a rating for a driver after a delivery is completed.
     * Rating must be between 1 and 5 inclusive.
     *
     * @param driver the Driver to rate
     * @param rating the rating score (1–5)
     */
    public void rateDriver(Driver driver, int rating) {
        // rate your driver
        if (rating >= 1 && rating <= 5) {
            driver.addRating(rating);
        }
    }

    /** @return the customer's delivery address */
    public String getDeliveryAddress() {
        // customer delivery address
        return deliveryAddress;
    }

    /** @param deliveryAddress the new delivery address */
    public void setDeliveryAddress(String deliveryAddress) {
        // set the delivery address
        this.deliveryAddress = deliveryAddress;
    }

    public void setGlobalData(GlobalData globalData) {
        this.globalData = globalData;
    }

    /** @return the customer's order history */
    public List<Order> getOrderHistory() {
        // get order history
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Customer: " + getName() + " | Address: " + deliveryAddress;
    }
}
