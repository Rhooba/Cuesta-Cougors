import java.util.Scanner;

/**
 * Entry point for the Online Food Delivery System.
 * Initializes the system, loads all data from files,
 * and launches the main application loop.
 *
 * Startup sequence:
 *   1. Create GlobalData and FileManager instances
 *   2. Load all data from text files
 *   3. Run the landing loop (login / create account / quit)
 *   4. On exit, save all data back to files
 *
 * @author
 * @version 1.1
 */
public class Main {

    public static void main(String[] args) {
        // Initialize the central data store and file I/O handler
        GlobalData globalData   = new GlobalData();
        FileManager fileManager = new FileManager();
        AccountCreation accountCreation = new AccountCreation(globalData, fileManager);

        // Load all persisted data (menu, users, drivers) from text files
        fileManager.loadAll(globalData);

        // One Scanner shared across the whole app — avoids input stream conflicts
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the Cuesta Cougars Food Delivery System ===");

        // Main landing loop — keeps running until the user chooses to quit
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // --- Login flow ---
                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    // Searches all user lists (customers, drivers, admins) for a match
                    User user = globalData.login(username, password);

                    if (user != null) {
                        System.out.println("\nWelcome back, " + user.getName() + "!");
                        user.getDashboard(scanner); // pass shared scanner into dashboard
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case "2":
                    // --- Account creation flow ---
                    System.out.println("Account type — (1) Customer  (2) Driver  (3) Admin");
                    System.out.print("Choose: ");
                    String type = scanner.nextLine().trim();

                    System.out.print("Choose a username: ");
                    String newUsername = scanner.nextLine();

                    System.out.print("Choose a password (min 6 characters): ");
                    String newPassword = scanner.nextLine();

                    System.out.print("Full name: ");
                    String newName = scanner.nextLine();

                    if (type.equals("1")) {
                        System.out.print("Delivery address: ");
                        String address = scanner.nextLine();
                        Customer c = accountCreation.createCustomer(newUsername, newPassword, newName, address);
                        System.out.println(c != null ? "Account created! Please log in." : "Username already taken.");
                    } else if (type.equals("2")) {
                        System.out.print("Current location: ");
                        String location = scanner.nextLine();
                        Driver d = accountCreation.createDriver(newUsername, newPassword, newName, location);
                        System.out.println(d != null ? "Driver account created! Please log in." : "Username already taken.");
                    } else if (type.equals("3")) {
                        Admin a = accountCreation.createAdmin(newUsername, newPassword, newName);
                        System.out.println(a != null ? "Admin account created! Please log in." : "Username already taken.");
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case "3":
                    // --- Quit ---
                    running = false;
                    break;

                default:
                    System.out.println("Please enter 1, 2, or 3.");
            }
        }

        // Persist all changes back to text files before exiting
        fileManager.saveAll(globalData);
        scanner.close();
        System.out.println("\nGoodbye! See you next time.");
    }
}
