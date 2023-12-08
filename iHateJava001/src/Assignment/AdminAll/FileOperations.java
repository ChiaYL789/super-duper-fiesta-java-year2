package Assignment.AdminAll;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.TableModel;

public class FileOperations {   
    
    // Declare ArrayList as class-level variable
    static ArrayList<String> usersList = new ArrayList<>();
   
    // Read Table JFrame
    public static void read2JFrame(String textfile, JFrame parentFrame) {
        List<String> content = readFile(textfile); //string the data from the user_data text file and read it, then put into table

        for (String item : content) {
            String[] userDetails = item.split(",");
            String userID = userDetails[0];
            String name = userDetails[1];
            String password = userDetails[2];
            String role = userDetails[3];
            double credit = Double.parseDouble(userDetails[4]);            
            //textArea
            JTextArea readTextArea = new JTextArea(content.toString());
            readTextArea.setWrapStyleWord(true);
            readTextArea.setLineWrap(true);
            readTextArea.setCaretPosition(0);
            readTextArea.setEditable(false);
            readTextArea.setFocusable(false);
            readTextArea.setFont(new Font("Comic MS", Font.PLAIN, 16));
            //ScrollPane    
            JScrollPane readScrollPane = new JScrollPane(readTextArea);
            readScrollPane.setPreferredSize(new Dimension(300, 300));
            readScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            readScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            readScrollPane.setWheelScrollingEnabled(true);
            //OptionPane    
            JOptionPane.showMessageDialog(parentFrame, readScrollPane, "File Contents", JOptionPane.INFORMATION_MESSAGE);
        }
    }
      
    // read file method
    public static List<String> readFile(String textfile) { // read file from textfile
        try (BufferedReader reader = new BufferedReader(new FileReader(textfile))) {
            List<String> content = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                content.add(line + "\n"); // write into the arraylist                        
            }
            reader.close();
            return content;
            
        }
        catch (IOException e) {
            return null;
        }
    }
      
    // registerNewUser method
    public static void registerNewUser(FileWriter writer, String textfile, String user, String pass, String power, double credit) {
        
        List<String> str = readFile(textfile);
        String prefix = "";
        int maxID = 0;
        int prevID = 0;
        switch (power) {
            case "Vendor" -> // If vendor role, prefix is VD + number,
                prefix = "VD";
            case "Customer" -> // If customer, CT
                prefix = "CT";
            case "Runner" -> // If runner, RN
                prefix = "RN";
            default ->
                System.out.println("Role not available"); 
        }
        
        // for loop to read the data and create latest userID, with the largest numeric number
        for (String item : str) {
            String[] userDetails = item.split(",");
            String pre = userDetails[0].substring(0, 2);
            if (pre.equals(prefix)) {
                String post = userDetails[0].substring(2);
                maxID = Integer.parseInt(post);
                if (maxID > prevID) {
                    prevID = maxID;
                }
            }
        }
        try { //write data into textfile (user_data.txt)
            writer.write(prefix + ++maxID + "," + user + "," + pass + "," + power + "," + credit + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // make another copy of registered user in anther text file method
    public static void registerUser2OtherTxt(String user, String power) throws IOException {
        // write user role into respective text file this is for vendor, vendorname+vendor(power), another write method
        switch (power) {
            case "Vendor":
                FileWriter vendorWriter = new FileWriter("food_menu.txt", true);
                vendorWriter.write("\n" + power + ":" + user);
                vendorWriter.close();
                break;
            case "Customer": //customer.txt change to customer make de xxx.txt
                FileWriter cusWriter = new FileWriter("customer.txt", true);
                cusWriter.write("\n" + power + ":" + user);
                cusWriter.close();
                break;
            case "Runner": ///runner.txt change to runner make de xxx.txt
                FileWriter runWriter = new FileWriter("runner.txt", true);
                runWriter.write("\n" + power + ":" + user);
                runWriter.close();
                break;
        }
    }
        
}
