package Assignment;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Customer {
    
    // Propeties
    private int customer_ID;
    private String customer_name;
    private String customer_username;
    private String customer_password;
    private ArrayList<Order> customer_orders;
    
    // Constructor
    public Customer(){}
    public Customer(int customer_ID, String customer_name, String customer_username, String customer_password){
        this.customer_ID = customer_ID;
        this.customer_name = customer_name;
        this.customer_username = customer_username;
        this.customer_password = customer_password;
    }
    
    // Constructor Temporarily Unavailable!
    // Issues: Still haven't customer database
    // Consturnctor for searching data without using method
    public Customer(String name){
        try{
            FileReader fileReader = new FileReader("user_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null){
                String[] dataParts = line.split(",");
                if (dataParts[3] == "customer")
            }
        }
    }
    
    // Getter and Setter
    public void setCustomerID(int customer_ID){
        this.customer_ID = customer_ID;
    }
    public int getCustomerID(){
        return this.customer_ID;
    }
    public void setCustomerName(String customer_name){
        this.customer_name = customer_name;
    }
    public String getCustomerName(){
        return this.customer_name;
    }
    public void setCustomerUsername(String customer_name){
        this.customer_username = customer_name;
    }
    public String getCustomerUsername(){
        return this.customer_username;
    }
    public void setCustomerPassword(String customer_password){
        this.customer_password = customer_password;
    }
    public String getCustomerPasword(){
        return this.customer_password;
    }
    
    // Find user in database [Use in empty constructor]
    public void searchUserData(String username){
        try{
            FileReader fileReader = new FileReader("user_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null){
                String[] dataParts = line.split(",");
                if (dataParts[3] == "customer"){
                    
                }
            }
    }
    
    // View menu
    public void ViewMenu(){
        // Menu method
    }
    
    // Read Customer Review
    public void ReadCusReview(){
        // Still haven't review database
    }
    
    // Review
    public void PlaceReview(String review){
        // Store review to notepad
    }
    
    
    // Place/ Cancle Order
    public void PlaceOrder(Order order){
        this.customer_orders.add(order);        
    }
    
    public void CancleOrder(Order order){
        this.customer_orders.remove(order);
    }
    
    // Check order status
    public String CheckOrderStatus(Order order){
        return order.getOrderStuatus();
    }
    
    // Check Order History
    public ArrayList CheckOrderHistory(Order order){
        return this.customer_orders;
    }
    
    // Check Transaction History
    // 蛤？
    
    
    public void ReadReview(){
        // Read data from notepad
    }
    
    // Reorder using order history
    // 不会做
    
}
