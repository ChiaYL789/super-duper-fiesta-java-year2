/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;

/**
 *
 * @author yehli
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class RegisterPanel extends JFrame implements ActionListener {
    
    String user;
    String pass;
    String power;
    
    ImageIcon logo;
    JLabel userLabel;
    JLabel passLabel;
    JTextField userIDField;
    JPasswordField userPasswordField; 
    JComboBox x;
    JButton registerButton;
    JButton resetButton;
    JMenuBar menuBar;
    JMenu Registration;
    JMenuItem Register;
    JMenuItem Update;
    JMenuItem Delete;
    JMenuItem Read;
    JMenu TopUp;
    JMenuItem TopUP;
   
    
    String textfile = RegisterPanel.FilePathChangeThis.renameThis;
      

    //////////file path class here
    class FilePathChangeThis {
            public static final String renameThis = "user_data.txt";            
    }    
    
    public RegisterPanel(){
    
        //combobox choose type of user registration
        String [] type = {"Vendor", "Customer", "Runner"};
        x = new JComboBox(type);
        x.setBounds(175, 200, 200, 25);
        x.addActionListener(this);        
        x.setSelectedIndex(0);
        
        //menu bar
        menuBar= new JMenuBar();        
        Registration = new JMenu("Option");
        TopUp = new JMenu("Top-Up");
        Register = new JMenuItem("Register");
        Update = new JMenuItem("Update");
        Delete = new JMenuItem("Delete");
        TopUP = new JMenuItem("Top-Up");
        Read = new JMenuItem("Read");
        
        Registration.addActionListener(this);
        TopUp.addActionListener(this);
        Register.addActionListener(this);
        Update.addActionListener(this);
        Delete.addActionListener(this);
        TopUP.addActionListener(this);
        Read.addActionListener(this);
        
        Registration.add(Register);
        Registration.add(Read);
        Registration.add(Update);
        Registration.add(Delete);
        TopUp.add(TopUP);
        
        menuBar.add(Registration);
        menuBar.add(TopUp);
        
        //username
        userLabel = new JLabel("Username: ");
        //setbounds
        userLabel.setBounds(50,100, 125,25);
        userLabel.setFont(new Font("Comic MS", Font.PLAIN, 20));
        
        userIDField = new JTextField();
        userIDField.setPreferredSize(new Dimension(250,40));
        userIDField.setFont(new Font("Consolas", Font.ITALIC, 20));
        userIDField.setForeground(Color.BLACK);
        userIDField.setBackground(Color.WHITE);
        userIDField.setCaretColor(Color.BLACK);
        //setbounds
        userIDField.setBounds(175, 100,200,25);
        userIDField.setEnabled(true);
        userIDField.setEditable(true);
                 
       
        
        //password
        passLabel = new JLabel("Password: ");
        //setbounds
        passLabel.setBounds(55,150, 125,25);
        passLabel.setFont(new Font("Comic MS", Font.PLAIN, 20));  
        
        this.userPasswordField = new JPasswordField();
        userPasswordField.setPreferredSize(new Dimension(250,40));
        userPasswordField.setFont(new Font("Consolas", Font.PLAIN, 20));
        userPasswordField.setForeground(Color.BLACK);
        userPasswordField.setBackground(Color.WHITE);
        userPasswordField.setCaretColor(Color.BLACK);
        //setbounds
        userPasswordField.setBounds(175, 150,200,25);
        userPasswordField.setEnabled(true);
        userPasswordField.setEditable(true);
      
        
        //remove spaces
        userIDField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                    e.consume(); // Consume the space character
                }
            }
        });
                
        userPasswordField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                    e.consume(); // Consume the space character
                }
            }
        });
        
        //register and reset button
        registerButton = new JButton("Register");
        registerButton.setBounds(250,250,100,25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);
        
        resetButton = new JButton("Reset");
        resetButton.setBounds(100,250,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        
        
        //panel customisation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Registration");
        logo = new ImageIcon("C:\\Users\\yehli\\Downloads\\rider.png");
        this.setIconImage(logo.getImage());
        
        
        this.add(userLabel);
        this.add(passLabel);
        this.add(userIDField);
        this.add(userPasswordField);
        this.add(x); //combobox
        this.add(registerButton);
        this.add(resetButton);
        this.setJMenuBar(menuBar);

        this.setVisible(true);
        
        
    }
    
    //create user method, write in the txt.file
    public static void registerUser(FileWriter writer, String user, String pass, String power) {
        try {
            writer.write("\n"+ user + "," + pass + "," + power + "\n");
        
        } catch (IOException e) {
        
        }finally {
            try{
                writer.close();
            }catch(IOException e){
                
            }
        }
    }
    
    //write user role into respective text file
    public static void writeUser(String user, String power) throws IOException{
        
        
        switch(power){
            case "Vendor":
                FileWriter vendorWriter = new FileWriter("food_menu.txt", true);
                vendorWriter.write("\n"+ power +":" + user);
                vendorWriter.close();
                break;
                
            case "Customer": //customer.txt change to customer make de xxx.txt
                FileWriter cusWriter = new FileWriter("customer.txt", true);
                cusWriter.write("\n"+ power +":" + user);
                cusWriter.close();
                break;
            
            case"Runner": ///runner.txt change to runner make de xxx.txt
                FileWriter runWriter = new FileWriter("runner.txt", true);
                runWriter.write("\n"+ power +":" + user);
                runWriter.close();
                break;
            }       
        }
        
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()== resetButton){//resetbutton
            userIDField.setText("");
            userPasswordField.setText("");
            x.setSelectedIndex(0);
            
        }
        
        if (e.getSource() == registerButton){//registerbutton
            
            try (FileWriter writer = new FileWriter(textfile, true)) {
                user = userIDField.getText();
                pass = new String(userPasswordField.getPassword());  
                power = x.getSelectedItem().toString();
                registerUser(writer, user, pass, power);
                x.setSelectedIndex(0);
                userIDField.setText("");
                userPasswordField.setText("");                
                JOptionPane.showMessageDialog(this, "Registration Successful");
                 
                writeUser(user, power);
                
            }
            catch (IOException ex) {
                System.out.println("Error: Unable to register user");              
            }
        }   
              
            
           
        
        //if menubar read pressed, read file
        if(e.getSource()==Read){
            readFile();
        }
        
        //menubar update pressed
        if(e.getSource()== Update){
            displayTable();
        }
        
        //menubar delete pressed
        if(e.getSource()== Delete){
            
        }
    } 
        
    
    // Read file (from .txt) method
    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(textfile))) {
            
            StringBuilder content = new StringBuilder();
            String line;
            

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            
                //display the data using text area
                JTextArea readTextArea = new JTextArea(content.toString());
                readTextArea.setWrapStyleWord(true);
                readTextArea.setLineWrap(true);
                readTextArea.setCaretPosition(0);
                readTextArea.setEditable(false);
                readTextArea.setFocusable(false);//maybethis can be true, coz can copy paste
                readTextArea.setFont(new Font("Comic MS", Font.PLAIN,16));
                
                //use scrollpane so able to read more data, can scroll
                JScrollPane readScrollPane = new JScrollPane(readTextArea);
                readScrollPane.setPreferredSize(new Dimension(300, 300));
                readScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                readScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                readScrollPane.setWheelScrollingEnabled(true);
                
                
                JOptionPane.showMessageDialog(this, readScrollPane, "File Contents", JOptionPane.INFORMATION_MESSAGE);
        
            
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: Unable to read the file.");
        }
    }
    
   

}
    //update file, need create 2 files, 2 txt, cannot modify the ori, so is direct create new, 
    
    //delete file
       
    
 
        


