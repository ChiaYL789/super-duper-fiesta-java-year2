package Assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Order data format is [Order ID, Food name, Customer name, Quantity, Status]

public class Order {
    
    // Order Propeties
    private int order_ID;
    private Food food;
    private String order_status;
    private Customer customer;
    private int quantity;
    
    // Constructor
    public Order(){ }
    public Order(int order_ID, Food food, Customer customer){
        this.order_ID = order_ID;
        this.food = food;
        this.customer = customer;
        order_status = "Pending";
    }
    
    // Getter and Setter
    public void setOrderID(int order_ID){
        this.order_ID = order_ID;
    }
    public int getOrderID(){
        return this.order_ID;
    }
    public void setFood(Food food){
        this.food = food;
    } 
    public Food getFood(){
        return this.food;
    }
    public void setOrderStatus(String status){
        this.order_status = status;
    }
    public String getOrderStuatus(){
        return this.order_status;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    
    // Places order into file
    // Data set [Order ID, Food name, Customer name, Quantity, Status]
    public String createOrder(){
        
        String status;
        
        // Try write data into txt file
        try{
            
            File fileName = new File("customer_order.txt");
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(order_ID + " " + food.getName() + " " + customer.getCustomerName() + " " + quantity + " " + order_status);
            printWriter.close();
            status = "File Write Successful!";
            
        } catch (IOException ex){ // Handle IOExceptions exceptions
            status = "File Write Error!";
            System.out.println(status);
            ex.printStackTrace();
        }
        
        return status; // return status of file writing
    }
    
    // View order
    // Return dataset as ArrayList, data is split by space " "
    public static ArrayList viewOrder(){
        
        // Define a ArrayList to return the data from the file
        ArrayList<String> orderList = new ArrayList();
        
        // Try read data from the file
        try{
            FileReader fileReader = new FileReader("customer_order.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            // Add data to ArrayList
            while ((line = bufferedReader.readLine()) != null){
                orderList.add(line);
                System.out.println(line);
            }
            bufferedReader.close();
            
        } catch (IOException ex){ // Handle IOExceptions exceptions
            ex.printStackTrace();
            System.out.println("Error in read file!");
            
        }
        return orderList;
    }
    
    // Update order status
    public String updateOrderStatus(String newStatus){
        
        String status = "";
        File fileName = new File("customer_order.txt");
        ArrayList<String> dataList;
        
        // Copy [all order] to dataList, change status on [this order]
        try{
            dataList = new ArrayList();
            String line;
            boolean modified = false;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Assign each data to line, split data to list
            while((line = bufferedReader.readLine()) != null){
                String[] dataParts = line.split(" ");
                int orderID = Integer.parseInt(dataParts[0]);
                if (orderID == this.order_ID){
                    dataList.add(order_ID + " " + food.getName() + " " + customer.getCustomerName() + " " + quantity + " " + status);
                    status = "Update Successful.";
                    modified = true;
                } else {
                    dataList.add(order_ID + " " + food.getName() + " " + customer.getCustomerName() + " " + quantity + " " + order_status);
                }
            }
            
            // If 
            if (modified == true){
                FileWriter fileWriter = new FileWriter(fileName, false);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                
                for (String order : dataList){
                    printWriter.println(order);
                }
                
                printWriter.close();
                System.out.println(status);
            } // else do nothing
        } catch (IOException ex){ 
            ex.printStackTrace();
        }
        
        return status;
    }
    
    // View order history
    public ArrayList viewHistory(String name){
        
        // Define a ArrayList to store [this order] data
        ArrayList<String> dataList = new ArrayList();
        
        // Try read the order data from txt file
        try{
            FileReader fileReader = new FileReader("customer_order.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            // Assign each data to line, split data to list
            while ((line = bufferedReader.readLine()) != null){
                String[] dataParts = line.split(" ");
                if (name.equals(dataParts[3])){
                    dataList.add(line);
                    System.out.println(line);
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        
        // Return order as ArrayList
        return dataList;
    }
}
