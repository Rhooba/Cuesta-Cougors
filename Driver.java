import java.util.LinkedList;

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
        // TODO: implement remaining initialization
    }

    /**
     * Displays the Driver dashboard.
     * Options: view assigned order, mark in-progress, mark delivered.
     */
    @Override
    public void getDashboard() {
        // TODO: implement
    }

    /**
     * Marks the assigned order's status as IN_PROGRESS.
     * Check that an order is actually assigned before updating.
     */
    public void markInProgress() {
        // TODO: implement
    }

    /**
     * Marks the assigned order's status as DELIVERED.
     * Sets the driver back to available and returns them to the driver pool.
     * Set assignedOrder to null after completion.
     */
    public void markDelivered() {
        // TODO: implement
        // Steps: set status, set isAvailable, clear assignedOrder, re-add to pool
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
        // TODO: implement
    }

    /**
     * Recalculates and stores the driver's average rating
     * based on all values currently in the ratings list.
     *
     * @return the computed average, or 0.0 if no ratings exist
     */
    public double calculateAverageRating() {
        return 0.0; // TODO: implement
    }

    /**
     * Compares this driver to another by average rating.
     * Used by the PriorityQueue to determine ordering.
     *
     * @param other the other Driver to compare
     * @return a negative, zero, or positive integer based on rating comparison
     */
    @Override
    public int compareTo(Driver other) {
        return 0; // TODO: implement — think about which direction gives you the driver you want first
    }

    /** @return true if driver is available for assignment */
    public boolean isAvailable() {
        return false; // TODO: implement
    }

    /** @param available the availability status to set */
    public void setAvailable(boolean available) {
        // TODO: implement
    }

    /** @return the driver's current average rating */
    public double getAverageRating() {
        return 0.0; // TODO: implement
    }

    /** @return the currently assigned Order, or null if none */
    public Order getAssignedOrder() {
        return null; // TODO: implement
    }

    /** @param order the Order to assign to this driver */
    public void setAssignedOrder(Order order) {
        // TODO: implement
    }

    /** @return the driver's current location */
    public String getCurrentLocation() {
        return null; // TODO: implement
    }

    /** @param location the new current location */
    public void setCurrentLocation(String location) {
        // TODO: implement
    }

    /** @return the full ratings linked list */
    public LinkedList<Integer> getRatings() {
        return null; // TODO: implement
    }

    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
