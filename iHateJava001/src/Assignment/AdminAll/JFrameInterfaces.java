package Assignment.AdminAll;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JFrameInterfaces extends JFrame implements ActionListener {   
     
    public JFrameInterfaces(){
    }
    
    //////////file path class here    
    String textfile = JFrameInterfaces.FilePathChangeThis.renameThis;
    class FilePathChangeThis {
        public static final String renameThis = "user_data.txt";            
    }    
    
    // variable declaration for initializeRead method, which calls the Read Class
    private Read read;

    public void initializeRead() {
        read = new Read();
    }      
    
    // variable declarations for RegisterPanel
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
    JMenuItem Modify;
    JMenuItem Read;
    JMenu TopUp;
    JMenuItem TopUP;
    JPanel contentPanel = new JPanel(new BorderLayout());
    
    // Register Panel Jframe interface
    public void RegisterPanel(){
    
        fop = new FileOperations();
        
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
        Modify = new JMenuItem("Modify");
        TopUP = new JMenuItem("Top-Up");
        Read = new JMenuItem("Read");
        
        Registration.addActionListener(this);
        TopUp.addActionListener(this);
        Register.addActionListener(this);
        Modify.addActionListener(this);
        TopUP.addActionListener(this);
        Read.addActionListener(this);
        
        Registration.add(Register);
        Registration.add(Read);
        Registration.add(Modify);
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
        userIDField.setFont(new Font("Consolas", Font.PLAIN, 12));
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
        userPasswordField.setFont(new Font("Consolas", Font.PLAIN, 12));
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
        
        //content panel
        contentPanel.setBounds(50, 300, 400, 300);
        contentPanel.setLayout(new BorderLayout()); // BorderLayout for simplicity

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
        this.add(contentPanel); 
        
        this.setVisible(true);                  
    
    }
    
    // variable declarations for top up menu
    JFrame jframe_TopUp;
    JPanel jpanel_TopUp;
    JLabel topUpAmountLabel; // this label only for labeling no usage
    JTextField creditInput; // input credit amount for topping up
    JButton topUpButton; // when clicked top up
    JButton receiptButton;
    JButton notifButton;

    private JLabel idLabel;
    private JLabel creditLabel; 
    private JComboBox<String> comboboxName;
    private final java.util.List<String[]> customerData = new ArrayList<>();
    private double currentCredit;

    // Top UP Menu JFrame Interface
    public void TopUpMenu(){
        

        java.util.List<String[]> customerData = readCustomerData(textfile);

        jframe_TopUp = new JFrame("Top-Up");
        jpanel_TopUp = new JPanel();
        comboboxName = new JComboBox<>();
        idLabel = new JLabel("Customer ID: ");
        creditLabel = new JLabel("Credit: ");

        for (String[] customer : customerData) {
            String cusName = customer[1];
            comboboxName.addItem(cusName);
        }

        comboboxName.setSelectedIndex(-1);
        comboboxName.addActionListener(e -> comboboxActionPerformed());
        topUpAmountLabel = new JLabel("Top-Up Amount: ");
        creditInput = new JTextField(15); //here input add to textfile
        topUpButton = new JButton("Top-Up"); //when press, credit input taken
        topUpButton.addActionListener(e -> topUpButtonActionPerformed());
        receiptButton = new JButton("Receipt");
        receiptButton.addActionListener(e -> receiptButtonActionPerformed());
        notifButton = new JButton("Send Notifications");
        notifButton.addActionListener(e -> notifButtonActionPerformed()); // send notifications button

        //topUpButton.addActionListener(e -> generateReceiptMethodHere);
        comboboxName.setPreferredSize(new Dimension(100, 30));
        idLabel.setPreferredSize(new Dimension(120, 30));
        creditLabel.setPreferredSize(new Dimension(100, 30));
        topUpAmountLabel.setPreferredSize(new Dimension(100, 30));
        jpanel_TopUp.setPreferredSize(new Dimension(400, 350));
        jpanel_TopUp.setBackground(Color.WHITE);
        jpanel_TopUp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        jpanel_TopUp.add(comboboxName, gbc);
        gbc.gridx = 1;
        jpanel_TopUp.add(idLabel, gbc);
        gbc.gridx = 2;
        jpanel_TopUp.add(creditLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        jpanel_TopUp.add(topUpAmountLabel, gbc);
        gbc.gridx = 1;
        jpanel_TopUp.add(creditInput, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        jpanel_TopUp.add(topUpButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        jpanel_TopUp.add(receiptButton, gbc);
        jframe_TopUp.add(jpanel_TopUp);
        jframe_TopUp.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        jframe_TopUp.setSize(500, 500);
        jframe_TopUp.setLocationRelativeTo(null);
        jframe_TopUp.setDefaultCloseOperation(jframe_TopUp.DISPOSE_ON_CLOSE);
        jframe_TopUp.setVisible(true);        
    }
    
    // action performed methods for top up menu
        private void comboboxActionPerformed() {
        int selectedIndex = comboboxName.getSelectedIndex();
        if (selectedIndex != -1) {
            String[] selectedCustomer = customerData.get(selectedIndex);
            String cusID = selectedCustomer[0];
            String credit = selectedCustomer[4];

            idLabel.setText("Customer ID: " + cusID);
            creditLabel.setText("Credit: " + credit);
        }
    }

    private void topUpButtonActionPerformed() {
        try {
            double topUpCreditAmount = Double.parseDouble(creditInput.getText());
            int selectedIndex = comboboxName.getSelectedIndex();
            if (selectedIndex != -1) {
                String[] selectedCustomer = customerData.get(selectedIndex);
                double currentCredit = Double.parseDouble(selectedCustomer[4]);
                double updatedCredit = currentCredit + topUpCreditAmount;
                selectedCustomer[4] = String.valueOf(updatedCredit);
                creditLabel.setText("Credit: " + updatedCredit);

                // Update the file with the modified data
                try (PrintWriter writer = new PrintWriter(new FileWriter(textfile))) {
                    for (String[] customer : customerData) {
                        // Write each customer data back to the file
                        for (int i = 0; i < customer.length; i++) {
                            writer.print(customer[i]);
                            if (i < customer.length - 1) {
                                writer.print(",");
                            }
                        }
                        writer.println(); // Move to the next line for the next customer
                    }
                    JOptionPane.showMessageDialog(null, "Top Up " + "RM " + topUpCreditAmount + " Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please input Amount ", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void receiptButtonActionPerformed() {
        ReceiptInterfaceJFrame();
        String credit = getCreditAmount();
        textareaReceipt.setText(" --------- RECEIPT ----------\n\n"
                + "Top Up Success: RM " + credit + "\n\n"
                + "   ----- THANK YOU! -----");        
       
    }
    
    private void notifButtonActionPerformed() {
        //send notifications method here
    }
    
    // Top up menu variable declarations
    JFrame jframe_Receipt;
    JPanel jpanel_Receipt;
    JTextArea textareaReceipt;
    
    // ReceiptInterface JFrame
    void ReceiptInterfaceJFrame() {
        textareaReceipt = new JTextArea("", 20, 5);
        textareaReceipt.setEditable(false);
        textareaReceipt.setFocusable(false);
        textareaReceipt.setBounds(10, 70, 290, 80);        

        jpanel_Receipt = new JPanel();
        jpanel_Receipt.add(textareaReceipt);
        jpanel_Receipt.setPreferredSize(new Dimension(400, 400));
        
        jframe_Receipt = new JFrame("Receipt");        
        jframe_Receipt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe_Receipt.getContentPane().add(jpanel_Receipt);
        jframe_Receipt.pack();
        jframe_Receipt.setLocationRelativeTo(null);
        jframe_Receipt.setVisible(true);
    }

    // get credit amount from textbox
    public String getCreditAmount() {
        return creditInput.getText();
    }

    // set text area receipt
    public void setTextAreaReceipt(String s) {
        textareaReceipt.setText(s);
    }
    
    // read CustomerData method
    public java.util.List<String[]> readCustomerData(String textfile) {
        try (BufferedReader br = new BufferedReader(new FileReader(textfile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[3].trim().equalsIgnoreCase("Customer")) {
                    String cusID = parts[0];
                    String cusName = parts[1];
                    String pass = parts[2];
                    String role = parts[3];
                    double credit = Double.parseDouble(parts[4]);
                    //credit += currentCredit;

                    customerData.add(new String[]{cusID, cusName, pass, role, String.valueOf(credit)});
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return customerData;
    }

    // variable declarations for actionPerformed method for RegisterPanel JFrame
    private FileOperations fop;
    private String user;
    private String pass;
    private String power;
    private int startingID;
    private double credit;
    
    // action performed methods for RegisterPanel JFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        fop = new FileOperations();

        if (e.getSource() == resetButton) { //resetbutton            
            userIDField.setText("");
            userPasswordField.setText("");
            x.setSelectedIndex(0);
        }
        else if (e.getSource() == registerButton) { //registerbutton            
            String userID = userIDField.getText();
            String password = new String(userPasswordField.getPassword());

            if (userID != null && !userID.isEmpty() && password != null && !password.isEmpty()) {
                try (FileWriter writer = new FileWriter(textfile, true)) {
                    user = userIDField.getText();
                    pass = new String(userPasswordField.getPassword());
                    power = x.getSelectedItem().toString();
                    fop.registerNewUser(writer, textfile, user, pass, x.getSelectedItem().toString(), credit); //from file operations

                    x.setSelectedIndex(0);
                    userIDField.setText("");
                    userPasswordField.setText("");
                    JOptionPane.showMessageDialog(this, "Registration Successful");
                    fop.registerUser2OtherTxt(user, power); //from file operations           
                }
                catch (IOException ex) {
                    System.out.println("Error: Unable to register user");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please fill in all the details!");
            }
        }
        else if (e.getSource() == Read) { //if menubar Read pressed, Read file
            initializeRead();
            read.ReadInterface();
        }
        else if (e.getSource() == Modify) { //menubar delete pressed     
            initializeRead();
            if (read != null) {
                read.UpdateAndDelete();
                EditModeManager.enterEditMode();
            }
        }
        else if (e.getSource() == TopUP) {
            TopUpMenu(); // TopUp Menu JFrame
            this.dispose();
        }
    }

}
