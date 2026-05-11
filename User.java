import java.util.Scanner;

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
        this.username = username;
        this.password = password;
        this.name = name;
    }

    /**
     * Displays the role-specific dashboard menu for this user.
     * Each subclass implements this differently.
     *
     * @param scnr the shared Scanner for reading user input
     */
    public abstract void getDashboard(Scanner scnr);

    /**
     * Validates the provided password against the stored password.
     *
     * @param inputPassword the password to check
     * @return true if the password matches, false otherwise
     */
    public boolean login(String inputPassword) {
        // .equals() compares the actual text content; == would only check if they're the same object in memory
        if (inputPassword.equals(this.password)) {
            return true;  // password matches — login succeeds
        } else {
            return false; // wrong password
        }
    }

    /** @return username as a String */
    public String getUsername() {
        return this.username;
    }

    /** @return the user's full name */
    public String getName() {
        return this.name;
    }

    /** @return password as a String */
    public String getPassword() {
        return this.password;
    }

    /** @param password the new password */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.username + ", " + this.name;
    }
}
