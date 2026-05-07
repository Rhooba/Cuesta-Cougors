import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Handles all file input and output for the food delivery system.
 * Reads and writes users, menu items, and orders to/from text files.
 *
 * File formats:
 *   menu.txt      — one item per line: "ItemName,price"
 *   customers.txt — one per line: "username,password,name,address"
 *   drivers.txt   — one per line: "username,password,name,location,rating1;rating2;..."
 *   admins.txt    — one per line: "username,password,name"
 *   orders.txt    — append-only log, one order per line
 *
 * Use try-with-resources for all file operations to ensure streams are closed properly.
 *
 * @author
 * @version 1.0
 */

public class FileManager {

    public static final String MENU_FILE      = "menu.txt";
    public static final String CUSTOMERS_FILE = "customers.txt";
    public static final String DRIVERS_FILE   = "drivers.txt";
    public static final String ADMINS_FILE    = "admins.txt";
    public static final String ORDERS_FILE    = "orders.txt";

    /**
     * Reads the menu from menu.txt and populates GlobalData.
     * Split each line by comma to get name and price.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadMenu(GlobalData globalData) {
        // try-with-resources: opens the file and automatically closes it when done
        try (Scanner scanner = new Scanner(new File(MENU_FILE))) {
            while (scanner.hasNextLine()) {       // keep reading until end of file
                String line = scanner.nextLine(); // read one full line of text
                String[] parts = line.split(","); // split "Burger,5.99" into ["Burger", "5.99"]
                if (parts.length == 2) {          // skip lines that don't have exactly 2 fields
                    String name = parts[0].trim();                    // remove any extra spaces around the name
                    double price = Double.parseDouble(parts[1].trim()); // convert "5.99" string to a double
                    globalData.addMenuItem(new MenuItem(name, price));  // build and store the menu item
                }
            }
        } catch (FileNotFoundException e) {
            // if the file doesn't exist yet, just warn instead of crashing
            System.out.println("Menu file not found: " + MENU_FILE);
        }
    }

    /**
     * Reads all customers from customers.txt and populates GlobalData with Customer objects.
     * Each line must follow the format: username,password,name,address
     * Lines that do not contain exactly 4 comma-separated fields are skipped silently.
     * Prints a message to stdout if the file is not found rather than throwing.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadCustomers(GlobalData globalData) {
        // try-with-resources: Scanner is closed automatically after the block finishes
        try (Scanner scanner = new Scanner(new File(CUSTOMERS_FILE))) {
            while (scanner.hasNextLine()) {       // loop until every line has been read
                String line = scanner.nextLine(); // grab the next line from the file
                String[] parts = line.split(","); // split "alice,pass,Alice,123 Main" into 4 parts
                if (parts.length == 4) {          // only process lines with all 4 expected fields
                    String username = parts[0].trim(); // index 0 = username
                    String password = parts[1].trim(); // index 1 = password
                    String name     = parts[2].trim(); // index 2 = display name
                    String address  = parts[3].trim(); // index 3 = delivery address
                    globalData.addCustomer(new Customer(username, password, name, address));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customers file not found: " + CUSTOMERS_FILE);
        }
    }
    

    /**
     * Reads all drivers from drivers.txt and populates GlobalData with Driver objects.
     * Each line must follow the format: username,password,name,location,rating1;rating2;...
     * Ratings are semicolon-separated integers (1–5) and are applied via addRating().
     * Each driver is added to the driver pool via globalData.addDriver(), which automatically
     * enqueues available drivers into the priority queue.
     * Lines that do not contain exactly 5 comma-separated fields are skipped silently.
     * Prints a message to stdout if the file is not found rather than throwing.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadDrivers(GlobalData globalData) {
        // try-with-resources: file is closed automatically even if an exception occurs
        try (Scanner scanner = new Scanner(new File(DRIVERS_FILE))) {
            while (scanner.hasNextLine()) {       // keep reading until end of file
                String line = scanner.nextLine(); // read one full line
                String[] parts = line.split(","); // split by comma — gives us 5 fields

                if (parts.length == 5) {          // skip any malformed lines
                    String username = parts[0].trim(); // index 0 = username
                    String password = parts[1].trim(); // index 1 = password
                    String name     = parts[2].trim(); // index 2 = display name
                    String location = parts[3].trim(); // index 3 = current location

                    // build the Driver object with the 4 constructor fields
                    Driver driver = new Driver(username, password, name, location);

                    // index 4 looks like "4;3;5" — split by ";" to get each rating separately
                    String[] ratings = parts[4].trim().split(";");
                    for (String r : ratings) {
                        // Integer.parseInt converts "4" (a String) to 4 (an int)
                        driver.addRating(Integer.parseInt(r));
                    }

                    // addDriver also enqueues the driver into the priority pool if available
                    globalData.addDriver(driver);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Drivers file not found: " + DRIVERS_FILE);
        }
    }

    /**
     * Reads all admins from admins.txt and populates GlobalData with Admin objects.
     * Each line must follow the format: username,password,name
     * Lines that do not contain exactly 3 comma-separated fields are skipped silently.
     * Prints a message to stdout if the file is not found rather than throwing.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadAdmins(GlobalData globalData) {
        // try-with-resources: Scanner is closed automatically after the block
        try (Scanner scanner = new Scanner(new File(ADMINS_FILE))) {
            while (scanner.hasNextLine()) {       // loop until every line is read
                String line = scanner.nextLine(); // read one line from the file
                String[] parts = line.split(","); // split "admin,pass,Bob" into 3 parts

                if (parts.length == 3) {          // skip lines missing any of the 3 fields
                    String username = parts[0].trim(); // index 0 = username
                    String password = parts[1].trim(); // index 1 = password
                    String name     = parts[2].trim(); // index 2 = display name

                    Admin admin = new Admin(username, password, name); // build the Admin object
                    globalData.addAdmin(admin);                        // register it in the system
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Admin file not found: " + ADMINS_FILE);
        }
    }

    /**
     * Appends a single order to orders.txt.
     * Use append mode so existing entries are not overwritten.
     *
     * @param order the Order to append
     */
    public void appendOrder(Order order) {
        // TODO: implement — use FileWriter with append = true
    }

    /**
     * Writes the current menu to menu.txt, overwriting the file.
     * Called after Admin adds, removes, or updates an item.
     *
     * @param globalData the GlobalData with the current menu
     */
    public void saveMenu(GlobalData globalData) {
        // TODO: implement
    }

    /**
     * Writes all customers to customers.txt, overwriting the file.
     *
     * @param globalData the GlobalData with the current customer list
     */
    public void saveCustomers(GlobalData globalData) {
        // FileWriter with no second argument defaults to overwrite mode (not append)
        try (FileWriter writer = new FileWriter(CUSTOMERS_FILE)) {
            for (Customer customer : globalData.getCustomers()) { // loop through every customer
                // build one comma-separated line and write it; "\n" moves to the next line
                writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getName() + "," + customer.getDeliveryAddress() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving information");
        }
    }

    /**
     * Writes all drivers to drivers.txt, overwriting the file.
     * Join each driver's ratings with ";" as a separator.
     *
     * @param globalData the GlobalData with the current driver list
     */
    public void saveDrivers(GlobalData globalData) {
        // PrintWriter wraps FileWriter to give us println(); FileWriter defaults to overwrite mode
        try (PrintWriter writer = new PrintWriter(new FileWriter(DRIVERS_FILE))) {
            for (Driver driver : globalData.getDrivers()) { // loop through every driver
                // build the ratings string manually as "4;3;5"
                String ratings = "";
                for (int r : driver.getRatings()) {
                    if (!ratings.isEmpty()) { // add a ";" before each rating except the first
                        ratings += ";";
                    }
                    ratings += r; // append the rating value
                }
                // write one comma-separated line per driver; println() adds the newline
                writer.println(
                    driver.getUsername()         + "," +
                    driver.getPassword()         + "," +
                    driver.getName()             + "," +
                    driver.getCurrentLocation()  + "," +
                    ratings);
            }
        } catch (IOException e) {
            // catches errors like disk full or permission denied
            System.out.println("Error saving drivers: " + DRIVERS_FILE);
        }
    }

    /**
     * Writes all admins to admins.txt, overwriting the file.
     * Each admin is written as a single line in the format: username,password,name
     * This format matches what loadAdmins() expects on the next startup.
     * Prints a message to stdout if the file cannot be written rather than throwing.
     *
     * @param globalData the GlobalData with the current admin list
     */
    public void saveAdmins(GlobalData globalData) {
        // PrintWriter wraps FileWriter to give us println() which adds the newline automatically
        // FileWriter with no second argument opens in overwrite mode, replacing the old file
        try (PrintWriter writer = new PrintWriter(new FileWriter(ADMINS_FILE))) {
            for (Admin admin : globalData.getAdmins()) { // loop through every admin
                // build the comma-separated line for this admin
                String line =
                        admin.getUsername() + "," +
                        admin.getPassword() + "," +
                        admin.getName();
                writer.println(line); // write the line and move to the next line
            }
        } catch (IOException e) {
            // catches errors like disk full or permission denied
            System.out.println("Error saving admins: " + ADMINS_FILE);
        }
    }

    /**
     * Loads all data into GlobalData at system startup.
     * Call this once from Main before launching the login loop.
     *
     * @param globalData the GlobalData instance to populate
     */
    public void loadAll(GlobalData globalData) {
        loadMenu(globalData);      // populate the menu from menu.txt
        loadCustomers(globalData); // populate customers from customers.txt
        loadDrivers(globalData);   // populate drivers from drivers.txt
        loadAdmins(globalData);    // populate admins from admins.txt
    }

    /**
     * Saves all user and menu data back to their files.
     * Call this on system shutdown or after significant data changes.
     *
     * @param globalData the GlobalData instance to persist
     */
    public void saveAll(GlobalData globalData) {
        saveMenu(globalData);      // write current menu back to menu.txt
        saveCustomers(globalData); // write all customers back to customers.txt
        saveDrivers(globalData);   // write all drivers back to drivers.txt
        saveAdmins(globalData);    // write all admins back to admins.txt
    }
}
