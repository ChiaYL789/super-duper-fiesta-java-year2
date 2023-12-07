package Assignment.AdminAll;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TopUpMenu  {

    String textfile = RegisterPanel.FilePathChangeThis.renameThis;

    JFrame jframe_TopUp;
    JPanel jpanel_TopUp;
    JLabel topUpAmountLabel; // this label only for labeling no usage
    JTextField creditInput; // input credit amount for topping up
    JButton topUpButton; // when clicked top up
    JButton receiptButton;
    JButton notifButton;

    private JLabel idLabel;
    private JLabel creditLabel; //
    private JComboBox<String> comboboxName;
    private final List<String[]> customerData = new ArrayList<>();
    private double currentCredit;

    //run main tester main method
//    public static void main(String[] args) {
//        new TopUpMenu();
//    }
    
    TopUpMenu() {

        List<String[]> customerData = readCustomerData(textfile);

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

    JFrame jframe_Receipt;
    JPanel jpanel_Receipt;
    JTextArea textareaReceipt;

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

    public List<String[]> readCustomerData(String textfile) {
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
}


    


