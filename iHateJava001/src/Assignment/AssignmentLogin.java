
package Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AssignmentLogin {
private static final String DATA_FILE = "C:\\Users\\chanw\\Desktop\\user_data.txt";  //file location

    public static String authenticate(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(","); //use "," as seperator
            if (parts.length == 3) {
                String storedUsername = parts[0];
                String storedPassword = parts[1];
                String role = parts[2];

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    System.out.println("Login successful! Your role is: " + role);
                    return role; // Return the user's role
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println("Login failed. Please check your username and passwordalsdjkfgsdkfjbasdiuf.");
    return null; // Return null if authentication fails
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();

        String userRole = authenticate(username, password);

        if (userRole != null) {
            // Based on the user's role, you can redirect to the appropriate interface.
            switch (userRole) {
                case "admin":
                    // Redirect to the admin interface
                    openAdminInterface();
                    break;
                case "customer":
                    // Redirect to the user interface
                    openUserInterface();
                    break;
                case "runner":
                    // Redirect to the manager interface
                    openManagerInterface();
                    break;
                case "vendor":
                    // Redirect to the manager interface
                    openVendorInterface();
                    break;
                    
                default:
                    System.out.println("Invalid user role.");
                    break;
            }
        }

        // Close the input scanner when done
        input.close();
    }
    
    public static void openAdminInterface(){
        System.out.println("Admin lolol");
    }
    public static void openUserInterface(){
        System.out.println("Customer mtfk");
    }
    public static void openManagerInterface(){
        System.out.println("Delivery Runner hahahahahahaah");
    }
    public static void openVendorInterface(){
        System.out.println("Vendor jiaikjia");
    }
    
}
