/**
 * Entry point for the Online Food Delivery System.
 * Initializes the system, loads all data from files,
 * and launches the login loop.
 *
 * Startup sequence:
 *   1. Create GlobalData and FileManager instances
 *   2. Load all data from text files
 *   3. Run the login loop
 *   4. On exit, save all data back to files
 *
 * @author
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        GlobalData globalData   = new GlobalData();
        FileManager fileManager = new FileManager();

        fileManager.loadAll(globalData);

        System.out.println("=== Welcome to the Food Delivery System ===");

        // TODO: implement login loop
        // Suggested structure:
        //
        // Scanner scanner = new Scanner(System.in);
        // boolean running = true;
        //
        // while (running) {
        //     System.out.print("Username: ");
        //     String username = scanner.nextLine();
        //
        //     System.out.print("Password: ");
        //     String password = scanner.nextLine();
        //
        //     User user = globalData.login(username, password);
        //
        //     if (user != null) {
        //         user.getDashboard();
        //     } else {
        //         System.out.println("Invalid credentials. Try again.");
        //     }
        // }
        //
        // fileManager.saveAll(globalData);
        // System.out.println("Goodbye!");
    }
}
