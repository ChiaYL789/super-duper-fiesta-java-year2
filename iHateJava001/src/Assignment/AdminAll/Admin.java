
package Assignment.AdminAll;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import java.io.*;

public class Admin extends JFrame{
        private Map<String, UserDetails> userDatabase; 

        public Admin() {
            userDatabase = new HashMap<>();
            loadUserDetailsFromFile();
        }

       
        public void updateUser(String userID, String newUsername, String newPassword, String newRole, double newCredit) {
            UserDetails userDetails = userDatabase.get(userID);
            if (userDetails != null) {
                userDetails.setName(newUsername);
                userDetails.setPassword(newPassword);
                userDetails.setRole(newRole);
                userDetails.setCredit(newCredit);
                saveUserDetailsToFile(); // Save updated user details to file
                System.out.println("User with ID " + userID + " updated successfully.");
            } else {
                System.out.println("User with ID " + userID + " not found.");
            }
        }
        
        // Method to save user details to a text file
        private void saveUserDetailsToFile() {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("user_details.txt")))) {
                for (Map.Entry<String, UserDetails> entry : userDatabase.entrySet()) {
                    UserDetails userDetails = entry.getValue();
                    writer.println(entry.getKey() + "," + userDetails.getName() + "," + userDetails.getPassword()
                            + "," + userDetails.getRole() + "," + userDetails.getCredit());
                }
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        // Method to load existing user details from the text file
        private void loadUserDetailsFromFile() {
            //UserDetails details = new UserDetails();
            //String name = details.getName();
            //String password = details.getPassword();
            try (Scanner scanner = new Scanner(new File("user_details.txt"))) {
                while (scanner.hasNextLine()) {
                    String[] userDetails = scanner.nextLine().split(",");
                    String userID = userDetails[0];
                    String name = userDetails[1];
                    String password = userDetails[2];
                    String role = userDetails[3];
                    double credit = Double.parseDouble(userDetails[4]);
                    userDatabase.put(userID, new UserDetails(name, password, role, credit));
                }
            } catch (FileNotFoundException e) {
                System.err.println("User details file not found. Creating a new file.");
            }
        }
        
        public void deleteUser(String userID) {
            if (userDatabase.containsKey(userID)) {
                userDatabase.remove(userID);
                saveUserDetailsToFile(); // Save user details to file after deletion
                System.out.println("User with ID " + userID + " deleted successfully.");
            } else {
                System.out.println("User with ID " + userID + " not found.");
            }
        }
        
        //call this at the topup interface
        public void topUpCustomerCredit(String userID, double amount) {
           UserDetails userDetails = userDatabase.get(userID);
           if (userDetails != null) {
               userDetails.setCredit(userDetails.getCredit() + amount);
               generateTransactionReceipt(userID, amount, "Top-up");
               sendReceiptToCustomer(userID, "Top-up");
               System.out.println("Credit topped up successfully for user: " + userID);
           } else {
               System.out.println("User with ID " + userID + " not found.");
           }
        }

        //call this method at the generate receipt interface
        private void generateTransactionReceipt(String userID, double amount, String action) { 
           // Logic to generate transaction receipt
           // Example: Printing receipt information
           System.out.println("Transaction Receipt:");
           System.out.println("User ID: " + userID);
           System.out.println("Amount: " + amount);
           System.out.println("Action: " + action);
        }

        private void sendReceiptToCustomer(String userID, String action) {
           // Logic to send receipt to customer through notification
           // Example: Sending notification to the user with userID about the action
           System.out.println("Receipt sent to user: " + userID + " for action: " + action);
        }
       
        private void sendNotification(UserDetails userDetails, String message) {
            // Simulating sending a notification by printing the message
            System.out.println("Notification sent to: " + userDetails.getName());
            System.out.println("Message:\n" + message);
            
            /*for message 
            String receiptContent = "Date: 2023-12-05\n" +
                        "Items purchased:\n" +
                        "- Product A: $10\n" +
                        "- Product B: $15\n" +
                        "Total amount: $25";
            */
        }


}
        