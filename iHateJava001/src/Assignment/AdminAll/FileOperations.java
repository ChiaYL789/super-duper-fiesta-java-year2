package Assignment.AdminAll;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FileOperations {    
        static ArrayList <String> usersList = new ArrayList <>();        
 
        //write user role into respective text file this is for vendor, vendorname+vendor(power)
        public static void writeUser(String user, String power) throws IOException{
            switch(power){
                case "Vendor":
                    FileWriter vendorWriter = new FileWriter("food_menu.txt", true);
                    vendorWriter.write("\n"+ power + ":" + user);
                    vendorWriter.close();
                    break;
                case "Customer": //customer.txt change to customer make de xxx.txt
                    FileWriter cusWriter = new FileWriter("customer.txt", true);
                    cusWriter.write("\n" + power + ":" + user);
                    cusWriter.close();
                    break;
                case"Runner": ///runner.txt change to runner make de xxx.txt
                    FileWriter runWriter = new FileWriter("runner.txt", true);
                    runWriter.write("\n" + power + ":" + user);
                    runWriter.close();
                    break;
            }       
        }
        
        public static void read2JFrame(String textfile,JFrame parentFrame){
            List<String> content = readFile(textfile);
            
            for(String item: content){
                        String[] userDetails = item.split(",");
                        String userID = userDetails[0];
                        String name = userDetails[1];
                        String password = userDetails[2];
                        String role = userDetails[3];
                        double credit = Double.parseDouble(userDetails[4]); 
                                    JTextArea readTextArea = new JTextArea(content.toString());
            readTextArea.setWrapStyleWord(true);
            readTextArea.setLineWrap(true);
            readTextArea.setCaretPosition(0);
            readTextArea.setEditable(false);
            readTextArea.setFocusable(false);
            readTextArea.setFont(new Font("Comic MS", Font.PLAIN,16));
            //ScrollPane    
            JScrollPane readScrollPane = new JScrollPane(readTextArea);
            readScrollPane.setPreferredSize(new Dimension(300, 300));
            readScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            readScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            readScrollPane.setWheelScrollingEnabled(true);
            //OptionPane    
            JOptionPane.showMessageDialog(parentFrame, readScrollPane, "File Contents", JOptionPane.INFORMATION_MESSAGE);

            }
                 // Assuming the UserDetails information is comma-separated in the file
 
            //textArea

        }
        
        //read file from textfile
        public static List<String> readFile(String textfile) {
                try (BufferedReader reader = new BufferedReader(new FileReader(textfile))) {
                        List<String> content = new ArrayList<>();
                        String line;
                        
                    while ((line = reader.readLine()) != null) 
                    {
                        content.add(line + "\n"); //write into the textfile
                        //newRegisteredUsersList.add(line); //write into the arraylist                        
                       
                    }
                       return content;              
                    
                } catch (IOException e) {
                    //JOptionPane.showMessageDialog(parentFrame, "Error: Unable to read the file.");
                    return null;
                }
        }
    
        //read file and display into table
        public static JTable displayTable(String textfile) {         
                JTable table = new JTable();
                DefaultTableModel model = new DefaultTableModel();
                try (BufferedReader reader = new BufferedReader(new FileReader(textfile))) {
                    String line;
                    while ((line = reader.readLine()) != null) 
                    {
                        String[] data = line.split("\t"); // Adjust delimiter based on your file content
                        model.addRow(data);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Unable to read the file.");
                }
                table.setModel(model);
                return table;
        }
    
        public static String getNextAvailableUserID (String textfile, String power) {   
                ArrayList<Integer> ids = new ArrayList<>();
                    try (BufferedReader bf = new BufferedReader (new FileReader(textfile))) {
                            String line;
                        while ((line = bf.readLine()) != null) {
                                String idPart = line.substring(2); // Get substring starting from index 2
                                ids.add(Integer.parseInt(idPart));
                        }
                    } catch (IOException | NumberFormatException ex) {
                            ex.printStackTrace(); // Handle exceptions (file not found, format issues, etc.)
                            System.out.println("Failed to read file correctly!");
                    }
                    
                    // Determine the next available ID for the given power
                    int nextUserID = 1; // Default starting value is 001
                    if (!ids.isEmpty()) {
                        int maxID = Collections.max(ids); // Get the maximum ID from the collected IDs
                        nextUserID = maxID + 1; // Increment the maximum ID for the next available ID
                        System.out.println("ADDED" + ids);
                    } else {
                        System.out.println("FAILEDADDING" + ids);
                    }                

                    String prefix = ""; // Determine prefix based on user power (Vendor(VD), Customer(CT), Runner(RN))             
                    switch (power) {
                        case "Vendor":
                            prefix = "VD";
                            break;
                        case "Customer":
                            prefix = "CT";
                            break;
                        case "Runner":
                            prefix = "RN";
                            break;
                        default:
                            System.out.println("Role not available");
                            break;
                    }                        
            return prefix + String.format("%03d", nextUserID);
        }
           
        //new registerUser method
        public static void registerUser(FileWriter writer, String textfile, String user, String pass, String power, double credit) {
            readFile();
            int staringID = 0;
            String nextUserID = generateUserID(power, startingID);
                try {
                    writer.write("\n" + nextUserID + "," + user + "," + pass + "," + power + "," + credit + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        
        //generate userID method
        private static String generateUserID(String power, int startingID) {
            String prefix = getPrefix(power);
            return prefix + String.format("%03d", startingID);
        }
        
        //get prefix
        private static String getPrefix(String power) {
            switch (power) {
                case "Vendor":
                    return "VD";
                case "Customer":
                    return "CT";
                case "Runner":
                    return "RN";
                default:
                    System.out.println("Invalid power specified");
            }
            return null;
        }
}
