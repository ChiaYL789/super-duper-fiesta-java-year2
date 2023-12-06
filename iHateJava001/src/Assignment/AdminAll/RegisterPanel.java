package Assignment.AdminAll;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegisterPanel extends JFrame implements ActionListener {    
    //////////file path class here    
    String textfile = RegisterPanel.FilePathChangeThis.renameThis;
    class FilePathChangeThis {
        public static final String renameThis = "user_data.txt";            
    }   

    private Read read;
    
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
    JPanel contentPanel = new JPanel(new BorderLayout());
    
    public RegisterPanel(){
    
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
        // call Read file when Read button(the up thr that button)
        //FileOperations.readFile(textfile, this); // 'this' refers to the RegisterPanel JFrame instance
        
        //call display table when update button
        //JTable table = FileOperations.displayTable(textfile);
    }
    
    public void initializeRead() {
        read = new Read();
    }
     
    private FileOperations fop;
    private String user;
    private String pass;
    private String power;
    private int startingID;
    private double credit;

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
        }
        else if (e.getSource() == Update) { //menubar update pressed            
            if(read != null){
                read.edit();
                EditModeManager.enterEditMode();
            }
        }
        else if (e.getSource() == Delete) { //menubar delete pressed            
             if(read != null){
                read.edit();
                EditModeManager.enterEditMode();
            }
        }
    }   
}

    /*(if remove this comment, remove one } above)
    //Getter and Setter for userid
    public String getUserid(){
        return userid;
    }
    
    public void setUserid(String userid){
        this.userid = userid;
    }

    
    // Getter and Setter for user
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // Getter and Setter for pass
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    // Getter and Setter for power
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    // Getter and Setter for userIDField
    public JTextField getUserIDField() {
        return userIDField;
    }

    public void setUserIDField(JTextField userIDField) {
        this.userIDField = userIDField;
    }

    // Getter and Setter for userPasswordField
    public JPasswordField getUserPasswordField() {
        return userPasswordField;
    }

    public void setUserPasswordField(JPasswordField userPasswordField) {
        this.userPasswordField = userPasswordField;
    }

    // Getter and Setter for textfile
    public String getTextfile() {
        return textfile;
    }

    public void setTextfile(String textfile) {
        this.textfile = textfile;
    }

    // Getter and setter for credit
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}*/
    
    
 
        


