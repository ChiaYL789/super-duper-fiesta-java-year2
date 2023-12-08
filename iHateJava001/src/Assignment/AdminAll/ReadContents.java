package Assignment.AdminAll;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

public class ReadContents extends JFrame implements ActionListener, MouseListener {

    ReadContents() {
    }

    // variable declarations for readInterface method
    JTable jtable;
    String[] col;
    Object[][] data;
    JFrame jframe_Read;
    JScrollPane jscrollpane_Read;

    // ReadContents Table interface 
    void ReadInterface() {

        jframe_Read = new JFrame("Read");
        col = new String[]{"UserID", "Name", "Password", "Role", "Credit"};
        data = getData();

        DefaultTableModel model = new DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //make table non editable
            }
        };

        jtable = new JTable(model);
        jtable.getTableHeader().setReorderingAllowed(false);
        jscrollpane_Read = new JScrollPane(jtable);

        jframe_Read.add(jscrollpane_Read);
        jframe_Read.pack();
        jframe_Read.setLocationRelativeTo(null);
        jframe_Read.setDefaultCloseOperation(jframe_Read.DISPOSE_ON_CLOSE);
        jframe_Read.setVisible(true);
    }

    // variable declarations for update&delete method
    JFrame jframe_Edit;
    JTextField idText_Edit;
    JTextField nameText_Edit;
    JTextField passText_Edit;
    JTextField roleText_Edit;
    JTextField creditText_Edit;
    JButton updateButton_Edit;
    JButton deleteButton_Edit;
    JScrollPane jscrollpane_Edit;

    // UpdateAndDelete method for update and delete 
    void UpdateAndDelete() {
        jframe_Edit = new JFrame("Edit");
        col = new String[]{"UserID", "Name", "Password", "Role", "Credit"};
        data = getData();

        DefaultTableModel model = new DefaultTableModel(data, col); // get table model
        model.setColumnIdentifiers(col); //set column identifiers        
        jtable = new JTable(model); // new table  
        jtable.addMouseListener(this); // add mouse listener        
        jtable.setBackground(Color.LIGHT_GRAY); // table customization
        jtable.setForeground(Color.black);
        jtable.setFont(new Font("Consolas", Font.PLAIN, 12));
        jtable.setRowHeight(30);
        jtable.setEnabled(true);

        idText_Edit = new JTextField(); // create JTextFields
        nameText_Edit = new JTextField();
        passText_Edit = new JTextField();
        roleText_Edit = new JTextField();
        creditText_Edit = new JTextField();
        updateButton_Edit = new JButton("Update"); // create JButtons
        deleteButton_Edit = new JButton("Delete");
        idText_Edit.setBounds(20, 220, 100, 25);// Jtextfields placements
        nameText_Edit.setBounds(20, 250, 100, 25);
        passText_Edit.setBounds(20, 280, 100, 25);
        roleText_Edit.setBounds(20, 310, 100, 25);
        creditText_Edit.setBounds(20, 340, 100, 25);
        updateButton_Edit.setBounds(150, 265, 100, 25); // JButtons placements
        deleteButton_Edit.setBounds(150, 310, 100, 25);
        updateButton_Edit.addActionListener(this); // JButtons add actionlistener
        deleteButton_Edit.addActionListener(this);

        jtable.setModel(model); // set the model into the table        
        jscrollpane_Edit = new JScrollPane(jtable); // create JScrollPane
        jscrollpane_Edit.setBounds(0, 0, 880, 200);

        jframe_Edit.add(jscrollpane_Edit); // add JscrollPane in this frame
        jframe_Edit.add(idText_Edit); // add JTextFields to this jframe
        jframe_Edit.add(nameText_Edit);
        jframe_Edit.add(passText_Edit);
        jframe_Edit.add(roleText_Edit);
        jframe_Edit.add(creditText_Edit);
        jframe_Edit.add(updateButton_Edit); // add JButtons to this jframe
        jframe_Edit.add(deleteButton_Edit);
        jframe_Edit.setLayout(null);
        jframe_Edit.setSize(900, 450);
        jframe_Edit.setLocationRelativeTo(null);
        jframe_Edit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jframe_Edit.setVisible(true);
    }

    // filepath 
    String textfile = JFrameInterfaces.FilePathChangeThis.renameThis;

    //clear text file before rewriting text file method
    public void clearTextFile(String textfile) {
        try (FileWriter fw = new FileWriter(textfile)) {

        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
 
    // nested class with user data details results, where the arraylist stores the user data read from the text file
    private class UserDataDetailsResults {

        private final List<String[]> filedata;

        public UserDataDetailsResults(List<String[]> filedata) {
            this.filedata = filedata;
        }

        public List<String[]> getFileData() {
            return filedata;
        }

        public void clearFileData() {
            filedata.clear();
        }
    }

    // user data details put into table method
    private UserDataDetailsResults userDataDetails(DefaultTableModel model) { //retrieve table data and put in arraylist
        int row = model.getRowCount();
        List<String[]> filedata = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            String UserID = model.getValueAt(i, 0).toString();
            String Name = model.getValueAt(i, 1).toString();
            String Password = model.getValueAt(i, 2).toString();
            String Role = model.getValueAt(i, 3).toString();
            String Credit = model.getValueAt(i, 4).toString();
            String[] userDetails = {UserID, Name, Password, Role, Credit};
            filedata.add(userDetails);
        }
        return new UserDataDetailsResults(filedata);
    }

    // save file method
    public static void saveNewFile(String textfile, DefaultTableModel model) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(textfile, true))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                StringJoiner joiner = new StringJoiner(",");
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object obj = model.getValueAt(row, col);
                    String value = obj == null ? "null" : obj.toString();   //Checks if 'obj' is null. If so, assigns "null" to 'value';
                    joiner.add(value);                             // otherwise, converts 'obj' to a string using its toString() method and sets that as 'value'.
                                                                            // This prevents potential NullPointerExceptions when handling 'obj'.                    
                }
                bw.write(joiner.toString());
                bw.newLine();
                bw.close();
            }
        }
        catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    // data retrieved converted into a  2d array list of "Objects"
    Object[][] getData() {
        try {
            String textfile = JFrameInterfaces.FilePathChangeThis.renameThis;
            BufferedReader br = new BufferedReader(new FileReader(textfile));
            ArrayList<String> list = new ArrayList();
            String str = "";
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            int n = list.get(0).split(",").length; //find the columns
            Object[][] data = new Object[list.size()][n]; //rows use size of the list
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i).split(",");
            }
            br.close();
            return data;
        }
        catch (IOException e) {
            return null;
        }
    }

    // (ActionEvent) action performed method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton_Edit) { // delete button            
            int i = jtable.getSelectedRow(); //i is the index of the selected row
            if (i >= 0) {
                clearTextFile(textfile);
                DefaultTableModel model = (DefaultTableModel) jtable.getModel();
                model.removeRow(i);
                saveNewFile("user_data.txt", model);
            }
            else {
                JOptionPane.showMessageDialog(null, "Failed to Delete User !", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource() == updateButton_Edit) {
            int i = jtable.getSelectedRow(); //i is the index of the selected row
            if (i >= 0) {                
                clearTextFile(textfile);
                DefaultTableModel model = (DefaultTableModel) jtable.getModel();
                model.setValueAt(idText_Edit.getText(), i, 0);
                model.setValueAt(nameText_Edit.getText(), i, 1);
                model.setValueAt(passText_Edit.getText(), i, 2);
                model.setValueAt(roleText_Edit.getText(), i, 3);
                model.setValueAt(creditText_Edit.getText(), i, 4);
                saveNewFile("user_data.txt", model);
            }
            else {
                JOptionPane.showMessageDialog(null, "Failed to Update User !", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // (MouseEvent) mouse clicked method
    @Override
    public void mouseClicked(MouseEvent e) {
        if (ReadContents.isInEditMode()) {
            int i = jtable.getSelectedRow(); ///i is the index of the selected row
            DefaultTableModel model = (DefaultTableModel) jtable.getModel();
            idText_Edit.setText(model.getValueAt(i, 0).toString());
            nameText_Edit.setText(model.getValueAt(i, 1).toString());
            passText_Edit.setText(model.getValueAt(i, 2).toString());
            roleText_Edit.setText(model.getValueAt(i, 3).toString());
            creditText_Edit.setText(model.getValueAt(i, 4).toString());
        }
        else {
            JOptionPane.showMessageDialog(null, "Failed to Enter Edit Mode !", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static boolean isInEditMode = false;

    public static void enterEditMode() {
        isInEditMode = true;
    }

    public static void exitEditMode() {
        isInEditMode = false;
    }

    public static boolean isInEditMode() {
        return isInEditMode;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
