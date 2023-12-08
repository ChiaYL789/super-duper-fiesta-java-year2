package Assignment.AdminAll;

import Assignment.Login.Login;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class JFrameInterfaces extends JFrame implements ActionListener {

    public JFrameInterfaces() {
    }

    // string filepath into string for easier usage throughout whole program
    String textfile = JFrameInterfaces.getTextFilePath.userDataTextFile;

    ///// file path class here
    class getTextFilePath {

        public static final String userDataTextFile = "user_data.txt";
    }

    // variable declaration for initializeRead method, which calls the ReadContents Class
    private ReadContents read;

    // initialize read method
    public void initializeRead() {
        read = new ReadContents();
    }

    // variable declarations for RegisterPanel JFrame
    ImageIcon logo;
    JLabel userLabel;
    JLabel passLabel;
    JTextField userIDField;
    JPasswordField userPasswordField;
    JComboBox x;
    JButton registerButton;
    JButton resetButton;
    JButton backButton;
    JMenuBar menuBar;
    JMenu Registration;
    JMenuItem Register;
    JMenuItem Modify;
    JMenuItem Read;
    JMenuItem TopUP;
    JPanel contentPanel = new JPanel(new BorderLayout());

    // Register Panel Jframe method
    public void RegisterPanel() {

        fop = new FileOperations();

        //combobox choose type of user registration
        String[] type = {"Vendor", "Customer", "Runner"};
        x = new JComboBox(type);
        x.setBounds(175, 200, 200, 25);
        x.addActionListener(this);
        x.setSelectedIndex(0);

        //menu bar
        menuBar = new JMenuBar();
        Registration = new JMenu("Option");
        Register = new JMenuItem("Register");
        Modify = new JMenuItem("Modify");
        TopUP = new JMenuItem("Top-Up");
        Read = new JMenuItem("Read");

        Registration.addActionListener(this);
        Register.addActionListener(this);
        Modify.addActionListener(this);
        TopUP.addActionListener(this);
        Read.addActionListener(this);

        Registration.add(Register);
        Registration.add(Read);
        Registration.add(Modify);
        Registration.add(TopUP);

        menuBar.add(Registration);

        //username
        userLabel = new JLabel("Username: ");
        //setbounds
        userLabel.setBounds(50, 100, 125, 25);
        userLabel.setFont(new Font("Comic MS", Font.PLAIN, 20));

        userIDField = new JTextField();
        userIDField.setPreferredSize(new Dimension(250, 40));
        userIDField.setFont(new Font("Consolas", Font.PLAIN, 12));
        userIDField.setForeground(Color.BLACK);
        userIDField.setBackground(Color.WHITE);
        userIDField.setCaretColor(Color.BLACK);
        //setbounds
        userIDField.setBounds(175, 100, 200, 25);
        userIDField.setEnabled(true);
        userIDField.setEditable(true);

        //password
        passLabel = new JLabel("Password: ");
        //setbounds
        passLabel.setBounds(55, 150, 125, 25);
        passLabel.setFont(new Font("Comic MS", Font.PLAIN, 20));

        this.userPasswordField = new JPasswordField();
        userPasswordField.setPreferredSize(new Dimension(250, 40));
        userPasswordField.setFont(new Font("Consolas", Font.PLAIN, 12));
        userPasswordField.setForeground(Color.BLACK);
        userPasswordField.setBackground(Color.WHITE);
        userPasswordField.setCaretColor(Color.BLACK);
        //setbounds
        userPasswordField.setBounds(175, 150, 200, 25);
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
        registerButton.setBounds(300, 250, 100, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setBounds(180, 250, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(60, 250, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

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
        this.add(backButton);
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
    private java.util.List<String[]> retrieveCustomerData = new ArrayList<>();
    private java.util.List<String[]> retrieveOtherData = new ArrayList<>();
    private double currentCredit;

    // Top UP Menu JFrame
    public void TopUpMenu() {

        // retrieve data from textfile, splitting into two parts, other and customer data
        Map<String, java.util.List<String[]>> retrievedMap = processData(textfile);
        java.util.List<String[]> retrieveCustomerData = retrievedMap.get("customerData");
        java.util.List<String[]> retrieveOtherData = retrievedMap.get("otherData");
        setRetrieveCustomerData(retrieveCustomerData); //setter for customer data
        setRetrieveOtherData(retrieveOtherData); //setter for other data

        jframe_TopUp = new JFrame("Top-Up");
        jpanel_TopUp = new JPanel();
        comboboxName = new JComboBox<>();
        idLabel = new JLabel("Customer ID: ");
        creditLabel = new JLabel("Credit: ");

        // retrieved customer data put into combobox
        if (!retrieveCustomerData.isEmpty()) {
            for (String[] customer : retrieveCustomerData) {
                if (customer.length > 1 && customer[1] != null && !customer[1].isEmpty()) {
                    String cusName = customer[1];
                    comboboxName.addItem(cusName);
                }
            }
        }
        else {
            System.out.println("No customer data available.");
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

        creditInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

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

        gbc.gridx = 2;
        jpanel_TopUp.add(topUpButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        jpanel_TopUp.add(notifButton, gbc);

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

    // setter method for customer data retrieval
    public void setRetrieveCustomerData(java.util.List<String[]> customerData) {
        this.retrieveCustomerData = customerData;
    }

    // getter method for customer data retrieval
    public java.util.List<String[]> getCustomerData() {
        return this.retrieveCustomerData;
    }

    // setter data method for other data retrieval
    public void setRetrieveOtherData(java.util.List<String[]> otherData) {
        this.retrieveOtherData = otherData;
    }

    // action performed methods for combobox at top up menu
    private void comboboxActionPerformed() { //when combobox selected, retrieve data based on customer name
        int selectedIndex = comboboxName.getSelectedIndex();
        if (selectedIndex != -1) {
            String[] selectedCustomer = retrieveCustomerData.get(selectedIndex);
            String cusID = selectedCustomer[0];
            String credit = selectedCustomer[4];

            idLabel.setText("Customer ID: " + cusID); // Display Customer ID at label
            creditLabel.setText("Credit: " + credit); // Display Customer available credit at label
            setCusID(cusID);
        }
    }

    //getter and setter for stored Customer ID
    private String storedCusID;

    private void setCusID(String cusID) {
        this.storedCusID = cusID;
    }

    private String getStoredCusID() {
        return storedCusID;
    }

    // action performed methods for topUp button at top up menu
    // when top up pressed, write customer data & other data back into text file
    private void topUpButtonActionPerformed() {
        try {
            double topUpCreditAmount = Double.parseDouble(creditInput.getText()); // retrieve amount inputed from textfield
            int selectedIndex = comboboxName.getSelectedIndex(); // retrieve the customer name from combobox
            if (selectedIndex != -1) {
                String[] selectedCustomer = retrieveCustomerData.get(selectedIndex);
                double currentCredit = Double.parseDouble(selectedCustomer[4]);
                double updatedCredit = currentCredit + topUpCreditAmount; // add-up both new and old credit
                selectedCustomer[4] = String.valueOf(updatedCredit);
                creditLabel.setText("Credit: " + updatedCredit); // update credit when topup success/ topup button pressed

                // Update the file with the modified data
                try (PrintWriter writer = new PrintWriter(new FileWriter(textfile))) {
                    // Write each customer data back to the file
                    for (String[] customer : retrieveCustomerData) {
                        for (int i = 0; i < customer.length; i++) {
                            writer.print(customer[i]);
                            if (i < customer.length - 1) {
                                writer.print(","); // Split data with ","
                            }
                        }
                        writer.println(); // Move to the next line for the next customer
                    }
                    // Writing otherData back to file
                    for (String[] other : retrieveOtherData) {
                        for (int i = 0; i < other.length; i++) {
                            writer.print(other[i]);
                            if (i < other.length - 1) {
                                writer.print(","); // Split data with ","
                            }
                        }
                        writer.println(); // Move to the next line for the next set of data
                    }
                    // top up success message dialog
                    JOptionPane.showMessageDialog(null, "Top Up " + "RM " + topUpCreditAmount + " Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (NumberFormatException e) { // if textbox empty, then unable to topup
            JOptionPane.showMessageDialog(null, "Please input Amount ", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    // action performed methods for receipt button at top up menu
    private void receiptButtonActionPerformed() {
        ReceiptInterfaceJFrame(); // call receipt Jframe
        String credit = getCreditAmount(); // get top up credit amount
        textareaReceipt.setText(" --------- RECEIPT ----------\n\n" // display receipt in textArea
                + "Top Up Success: RM " + credit + "\n\n"
                + "   ----- THANK YOU! -----");
    }

    // action performed methods for notif button at top up menu
    // write into notif.txt
    private void notifButtonActionPerformed() {
        try {
            FileWriter notifWriter = new FileWriter("D:\\APU YEAR2\\Java\\ilovajava\\newJava\\iHateJava001\\notif.txt", true);
            notifWriter.write("Admin" + "," + storedCusID + "," + "Successfully Top-Up" + "\n");
            notifWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Notifications Sent To Customer!");
        // notification success pop up message dialog
    }

    // Receipt JFrame @ TopUP menu variable declarations
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
        jframe_Receipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jframe_Receipt.getContentPane().add(jpanel_Receipt);
        jframe_Receipt.pack();
        jframe_Receipt.setLocationRelativeTo(null);
        jframe_Receipt.setVisible(true);
    }

    // get credit amount from textbox method
    public String getCreditAmount() {
        return creditInput.getText();
    }

    // set text area receipt method
    public void setTextAreaReceipt(String s) {
        textareaReceipt.setText(s);
    }

    // split read data into two files for rewrite later, use getter and setter methods
    public Map<String, java.util.List<String[]>> processData(String textfile) {
        Map<String, java.util.List<String[]>> dataMap = new HashMap<>();
        java.util.List<String[]> customerData = new ArrayList<>();
        java.util.List<String[]> otherData = new ArrayList<>();

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

                    customerData.add(new String[]{cusID, cusName, pass, role, String.valueOf(credit)});

                }
                else if (parts.length == 5 && !parts[3].trim().equalsIgnoreCase("Customer")) {
                    String otherID = parts[0];
                    String otherName = parts[1];
                    String otherpass = parts[2];
                    String otherRole = parts[3];
                    double otherCredit = Double.parseDouble(parts[4]);

                    otherData.add(new String[]{otherID, otherName, otherpass, otherRole, String.valueOf(otherCredit)});
                }
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // put both list into the map
        dataMap.put("customerData", customerData);
        dataMap.put("otherData", otherData);
        return dataMap;

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

        if (e.getSource() == resetButton) { // resetbutton clicked, clear both textFields
            userIDField.setText("");
            userPasswordField.setText("");
            x.setSelectedIndex(0);
        }
        else if (e.getSource() == backButton) { // back button clicked, exit to login interface
            Login login = new Login(); // create new instance of login main class
            login.setVisible(true); //call class
            this.dispose(); // close the register panel JFrame
        }
        else if (e.getSource() == registerButton) { // registerbutton
            String userName = userIDField.getText(); // string both user and pass textfield into 2 variables(userName&password)
            String password = new String(userPasswordField.getPassword());

            // if statement to ensure both textfields are not empty, both need to have input
            if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
                try (FileWriter writer = new FileWriter(textfile, true)) {
                    user = userIDField.getText();
                    pass = new String(userPasswordField.getPassword());
                    power = x.getSelectedItem().toString();
                    fop.registerNewUser(writer, textfile, user, pass, x.getSelectedItem().toString(), credit); // registerNewUser method from file operations

                    // After finish writing data, reset textfields and combobox to first index (Vendor)
                    x.setSelectedIndex(0);
                    userIDField.setText("");
                    userPasswordField.setText("");
                    JOptionPane.showMessageDialog(this, "Registration Successful");
                    fop.registerUser2OtherTxt(user, power); // Write user data to a different text file method from file operations, which is used by other roles
                }
                catch (IOException ex) {
                    System.out.println("Error: Unable to register user");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please fill in all the details!");
            }
        }
        else if (e.getSource() == Read) { //if menubar ReadContents pressed, ReadContents file
            initializeRead();
            read.ReadInterface(); // launch the read JFrame with table with data populated
        }
        else if (e.getSource() == Modify) { //menubar modify pressed
            initializeRead();
            if (read != null) {
                read.UpdateAndDelete(); // display JFrame for update and deleting
                ReadContents.enterEditMode(); // enter edit mode of read contents
            }
        }
        else if (e.getSource() == TopUP) { // if topUp press
            TopUpMenu(); // display TopUp Menu JFrame
        }
    }
}
