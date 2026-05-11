import java.util.LinkedList;
import java.util.Scanner;

/**
 * Represents a Driver user in the food delivery system.
 * Drivers are assigned orders and can update order status.
 *
 * Ratings are stored in a LinkedList capped at MAX_RATINGS entries.
 * When a new rating arrives and the list is full, the oldest is removed first.
 *
 * @author
 * @version 1.0
 */
public class Driver extends User implements Comparable<Driver> {

    private boolean isAvailable;
    private LinkedList<Integer> ratings;
    private double averageRating;
    private Order assignedOrder;
    private String currentLocation;
    private GlobalData globalData; //We may or may not want this, but for now it's useful

    private static final int MAX_RATINGS = 10;

    /**
     * Constructs a Driver with the given credentials.
     * Driver starts as available with no ratings and no assigned order.
     *
     * @param username        the driver's login username
     * @param password        the driver's password
     * @param name            the driver's full name
     * @param currentLocation the driver's current location
     */
    public Driver(String username, String password, String name, String currentLocation) {
        super(username, password, name);
        this.ratings = new LinkedList<>();
        this.isAvailable = true;
        this.averageRating = 0.0;
        this.assignedOrder = null;
        this.currentLocation = currentLocation;
    }

    /**
     * Displays the Driver dashboard.
     * Options: view assigned order, mark in-progress, mark delivered.
     *
     * @param scnr the shared Scanner for reading user input
     */
    @Override
    public void getDashboard(Scanner scanner) {
        while (true) {
            // print the menu options for the driver to choose from
            System.out.println("\n--- Driver Dashboard ---");
            System.out.println("Your current rating: " + String.format("%.2f", averageRating));
            System.out.println("Availability: " + (isAvailable ? "Available" : "Unavailable"));
            System.out.println("1. View assigned order");
            System.out.println("2. Mark order in progress");
            System.out.println("3. Mark order delivered");
            System.out.println("4. Toggle availability");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            // read the driver's input and remove any extra spaces with trim()
            String choice = scanner.nextLine().trim();

            // switch checks the value of choice and runs the matching case
            switch (choice) {
                case "1":
                    // assignedOrder is null if no order has been assigned yet
                    if (assignedOrder != null) {
                        System.out.println(assignedOrder.toString()); // print order details
                    } else {
                        System.out.println("No order currently assigned.");
                    }
                    break; // break stops the switch from falling through to the next case
                case "2":
                    markInProgress();                          // sets order status to PICKED_UP
                    System.out.println("Your order is in progress.");
                    break;
                case "3":
                    markDelivered();                           // sets status to DELIVERED, frees driver
                    System.out.println("Your order has been delivered!");
                    break;
                case "4":
                    toggleAvailability();
                    break;
                case "5":
                    System.out.println("Logging out...");      // return exits the loop and the method
                    return;
                default:
                    // runs if the input didn't match any of the cases above
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Marks the assigned order's status as IN_PROGRESS.
     * Check that an order is actually assigned before updating.
     */
    public void markInProgress() {
        if (assignedOrder == null) {
            System.out.println("No order currently assigned.");
            return;
        }
        assignedOrder.setStatus(OrderStatus.PICKED_UP);
    }

    /**
     * Marks the assigned order's status as DELIVERED.
     * Sets the driver back to available and returns them to the driver pool.
     * Set assignedOrder to null after completion.
     */
    public void markDelivered() {
        if (assignedOrder == null) {
            System.out.println("No order currently assigned.");
            return;
        }
        assignedOrder.setStatus(OrderStatus.DELIVERED); // mark the order as delivered
        isAvailable = true;                             // driver is now free to take new orders
        assignedOrder = null;                           // clear the order reference
        globalData.addDriverToPool(this);               // re-add this driver to the available pool
    }

    /**
     * Toggles the driver's availability status.
     * If currently available, sets to unavailable and removes from pool.
     * If currently unavailable, sets to available and adds to pool.
     */
    public void toggleAvailability() {
        setAvailable(!isAvailable);
        System.out.println("Availability set to: " + (isAvailable ? "Available" : "Unavailable"));
    }

    /**
     * Adds a new rating to this driver's ratings list.
     * If the list is already at MAX_RATINGS, remove the oldest before adding the new one.
     * Recalculate the average after every update.
     *
     * Time Complexity:  O(n) for recalculating average, O(1) for add/remove
     * Space Complexity: O(1) — list size is capped at MAX_RATINGS
     *
     * @param rating the new rating value (1–5)
     */

    
    public void addRating(int rating) {
        // reject anything outside the 1–5 range before it corrupts the average
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        // if the list is full, drop the oldest rating to make room — this is a sliding window
        if (ratings.size() >= MAX_RATINGS) {
            ratings.removeFirst(); // removeFirst() removes the oldest entry (front of the LinkedList)
        }
        ratings.add(rating);       // add the new rating to the back of the list
        calculateAverageRating();  // recompute the average with the updated list
    }

    /**
     * Recalculates and stores the driver's average rating
     * based on all values currently in the ratings list.
     *
     * @return the computed average, or 0.0 if no ratings exist
     */
    public double calculateAverageRating() {
        // if there are no ratings yet, average is 0 — avoid divide-by-zero
        if (ratings.isEmpty()) {
            averageRating = 0.0;
            return 0.0;
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating; // add each rating to the running total
        }
        // cast sum to double before dividing so we get a decimal result, not an integer
        averageRating = (double) sum / ratings.size();
        return averageRating;
    }

    /**
     * Compares this driver to another by average rating.
     * Used by the PriorityQueue to determine ordering.
     * Drivers with higher ratings (closer to 5) are prioritized first.
     *
     * @param other the other Driver to compare
     * @return a negative, zero, or positive integer based on rating comparison
     */
    @Override
    public int compareTo(Driver other) {
        // PriorityQueue is a min-heap by default (lowest value comes out first)
        // by putting "other" before "this" we reverse the order so higher ratings come out first
        return Double.compare(other.averageRating, this.averageRating);
    }

    /** @return true if driver is available for assignment */
    public boolean isAvailable() {
        return isAvailable;
    }

    /** @param available the availability status to set */
    //Now a very important method, meant to only have available drivers in the pool, we'll see how this goes
    public void setAvailable(boolean available) {
        this.isAvailable = available;
        
        // Update driver pool if GlobalData is set
        if (globalData != null) {
            if (available) {
                // Add driver back to pool when they become available
                globalData.addDriverToPool(this);
            } else {
                // Remove driver from pool when they become unavailable
                globalData.removeDriverFromPool(this);
            }
        }
    }

    /** @return the driver's current average rating */
    public double getAverageRating() {
        return averageRating;
    }

    /** @return the currently assigned Order, or null if none */
    public Order getAssignedOrder() {
        return assignedOrder;
    }

    /** @param order the Order to assign to this driver */
    public void setAssignedOrder(Order order) {
        this.assignedOrder = order;
    }

    /** @return the driver's current location */
    public String getCurrentLocation() {
        return currentLocation;
    }

    /** @param location the new current location */
    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }

    /** @return the full ratings linked list */
    public LinkedList<Integer> getRatings() {
        return ratings;
    }

    //Drivers need a globalData reference, so this might be nice
    public void setGlobalData(GlobalData globalData) {
        this.globalData = globalData;
    }

    @Override
    public String toString() {
        return getUsername()    + " | " +
               getName()       + " | " +
               currentLocation + " | " +
               averageRating   + " | " +
               isAvailable;
    }
}
