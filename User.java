/**
 * Abstract base class representing a user in the food delivery system.
 * All user types extend this class.
 *
 * @author
 * @version 1.0
 */
public abstract class User {

    private String username;
    private String password;
    private String name;

    /**
     * Constructs a User with the given credentials and name.
     *
     * @param username the unique login username
     * @param password the user's password
     * @param name     the user's full name
     */
    public User(String username, String password, String name) {
        // TODO: implement
    }

    /**
     * Displays the role-specific dashboard menu for this user.
     * Each subclass implements this differently.
     */
    public abstract void getDashboard();

    /**
     * Validates the provided password against the stored password.
     *
     * @param inputPassword the password to check
     * @return true if the password matches, false otherwise
     */
    public boolean login(String inputPassword) {
        return false; // TODO: implement
    }

    /** @return username as a String */
    public String getUsername() {
        return null; // TODO: implement
    }

    /** @return the user's full name */
    public String getName() {
        return null; // TODO: implement
    }

    /** @return password as a String */
    public String getPassword() {
        return null; // TODO: implement
    }

    /** @param password the new password */
    public void setPassword(String password) {
        // TODO: implement
    }

    @Override
    public String toString() {
        return null; // TODO: implement
    }
}
