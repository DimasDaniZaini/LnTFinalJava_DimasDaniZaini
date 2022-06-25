package Element;

import Database.User;
import Database.database;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends JFrame implements ActionListener{

    private final User currentUser;

    public JLabel rowValue, usernameValue, emailValue;
    public JTable userTable;
//    public JButton logoutBtn, deleteBtn;

    // Field
//    public JTextField usernameField = new JTextField();
//    public JPasswordField passwordField = new JPasswordField();


    database database_ = new database();

    // Btn
    public JButton addToDoBtn, deleteToDoBtn, refreshBtn, historyBtn, saveButton;


    public Dashboard(User currentUser) {
        this.currentUser = currentUser;

        this.setTitle("Dasboard");
        this.setSize(1000, 800);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.buildFrame();

        this.setVisible(true);
    }

//    private JPanel createTablePanel(int baseMargin) {
////        String[] columns = {"Username", "Email", "Password"};
////        DefaultTableModel model = new DefaultTableModel(columns, 0);
////
////        List<User> userList = Main.USER_HANDLER.getUsers();
////        for (User user : userList) {
////            Object[] data = {user.getUsername(), user.getEmail(), user.getPassword()};
////            model.addRow(data);
////        }
////
////        userTable = new JTable(model);
////        JScrollPane scrollPane = new JScrollPane(userTable);
////
////        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
////        userTable.addMouseListener(new MouseAdapter() {
////            @Override
////            public void mouseReleased(MouseEvent e) {
////                int row = userTable.getSelectedRow();
////
////                String username = (String) userTable.getValueAt(row, 0);
////                String email = (String) userTable.getValueAt(row, 1);
////
////                rowValue.setText(row + "");
////                usernameValue.setText(username);
////                emailValue.setText(email);
////            }
////        });
////
////        JPanel centerPanel = new JPanel(new BorderLayout());
////        centerPanel.setBorder(BorderFactory.createEmptyBorder(
////                0,
////                baseMargin,
////                10,
////                baseMargin
////        ));
////        centerPanel.add(scrollPane);
////
////        return centerPanel;
//    }

//    private JPanel createBottomPanel(int baseMargin) {
////        JPanel dataViewPanel = new JPanel(new GridLayout());
////        JPanel controlViewPanel = new JPanel(new GridLayout());
////
////        JPanel wrappedDataPanel = new JPanel(new GridLayout(3, 2));
////        JPanel wrappedControlPanel = new JPanel(new GridLayout(2, 1, 0, 20));
////
////        JLabel rowLabel = new JLabel("Row:");
////        JLabel usernameLabel = new JLabel("Username:");
////        JLabel emailLabel = new JLabel("Email address:");
////
////        rowValue = new JLabel();
////        usernameValue = new JLabel();
////        emailValue = new JLabel();
////
////        wrappedDataPanel.add(rowLabel);
////        wrappedDataPanel.add(rowValue);
////
////        wrappedDataPanel.add(usernameLabel);
////        wrappedDataPanel.add(usernameValue);
////
////        wrappedDataPanel.add(emailLabel);
////        wrappedDataPanel.add(emailValue);
////
////        logoutBtn = new JButton("LOGOUT");
////        deleteBtn = new JButton("DELETE");
////
////        logoutBtn.setPreferredSize(new Dimension(0, 30));
////        logoutBtn.addActionListener(this);
////        deleteBtn.addActionListener(this);
////
////        wrappedControlPanel.add(logoutBtn);
////        wrappedControlPanel.add(deleteBtn);
////
////        wrappedDataPanel.setBorder(BorderFactory.createEmptyBorder(
////                baseMargin,
////                baseMargin,
////                baseMargin,
////                baseMargin
////        ));
////        wrappedControlPanel.setBorder(BorderFactory.createEmptyBorder(
////                baseMargin,
////                baseMargin * 4,
////                baseMargin,
////                baseMargin * 4
////        ));
////
////        dataViewPanel.add(wrappedDataPanel);
////        controlViewPanel.add(wrappedControlPanel);
////
////        dataViewPanel.setBorder(BorderFactory.createTitledBorder("Data View"));
////        controlViewPanel.setBorder(BorderFactory.createTitledBorder("Control View"));
////
////        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
////
////        bottomPanel.add(dataViewPanel);
////        bottomPanel.add(controlViewPanel);
////
////        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
////                0,
////                baseMargin,
////                baseMargin,
////                baseMargin
////        ));
////
////        return bottomPanel;
//    }

    private void buildFrame() {

        int centerMargin = 40;

        // Label Text
        JLabel titleLabel = new JLabel("Hello, " + currentUser.getUsername(), JLabel.CENTER);
        JButton toDoLabel = new JButton("To-Do List");
        JLabel messageLabel = new JLabel(currentUser.getMessage());
        JLabel expireLabel;
        if (!messageLabel.equals(""))
            expireLabel = new JLabel("Expire time : " + currentUser.getExpire());
        else
            expireLabel = new JLabel("");

        // Panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel centerPanel = new JPanel(new GridLayout(2,1, -10, 20)); // Defaultnya FlowLayout
        JPanel bottomPanel = new JPanel(new GridLayout(3, 2)); // Atur row & colum
        JPanel buttonPanel = new JPanel(); // Pakai flow layout, udah default



        // Panel Customisation
        topPanel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, centerMargin, centerMargin, centerMargin
        ));

        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, centerMargin, centerMargin, centerMargin
        )); // Atur Margin

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0, centerMargin, centerMargin, centerMargin
        ));


        // Bottom
        addToDoBtn = new JButton("Add To-Do");
        deleteToDoBtn = new JButton("Delete To-Do");
        refreshBtn = new JButton("Refresh");
        historyBtn = new JButton("History");
        saveButton = new JButton("Save Changes");


        // Label Customise
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        toDoLabel.setBorder(BorderFactory.createEmptyBorder(
                10, 0, 10, 0
        ));
        toDoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        expireLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Button Customise
        toDoLabel.setBorder(null);
        toDoLabel.setContentAreaFilled(false);
        toDoLabel.setBackground(Color.white);

        addToDoBtn.setBackground(Color.white);
        addToDoBtn.setBorder(null);;

        deleteToDoBtn.setBackground(Color.white);
        deleteToDoBtn.setBorder(null);;

        refreshBtn.setBackground(Color.white);
        refreshBtn.setBorder(null);;

        historyBtn.setBackground(Color.white);
        historyBtn.setBorder(null);;

        saveButton.setBackground(Color.white);
        saveButton.setBorder(null);;


        // Top Panel
        topPanel.add(titleLabel);
        topPanel.add(toDoLabel);

        // Center Panel
        centerPanel.add(messageLabel);
        centerPanel.add(expireLabel);

        // Button Panel
        bottomPanel.add(addToDoBtn);
        bottomPanel.add(deleteToDoBtn);
        bottomPanel.add(refreshBtn);
        bottomPanel.add(historyBtn);
        bottomPanel.add(saveButton);

        // Add to GUI
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Biar kalao dipencet, maka ...
        addToDoBtn.addActionListener(this);
        deleteToDoBtn.addActionListener(this);
        saveButton.addActionListener(this);
        historyBtn.addActionListener(this);
        refreshBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton){
            JOptionPane.showMessageDialog(
                    this, "To Do Successfully Saved!",
                    "Success",JOptionPane.INFORMATION_MESSAGE
            );
        }
        if (e.getSource() == historyBtn){
            JOptionPane.showMessageDialog(
                    this, "Ga kebayang bentuknya gmn :-(",
                    "Success",JOptionPane.INFORMATION_MESSAGE
            );
        }
        if (e.getSource() == refreshBtn){
            JOptionPane.showMessageDialog(
                    this, "Ga kebayang bentuknya gmn :-(",
                    "Success",JOptionPane.INFORMATION_MESSAGE
            );
        }

        if (e.getSource() == addToDoBtn) {
            new AddToDo(currentUser);
            this.dispose();
        }

        if (e.getSource() == deleteToDoBtn) {

            int choose = JOptionPane.showConfirmDialog(
                    this, "Are You Sure Want to Delete This?",
                    "Select",JOptionPane.YES_NO_OPTION
            );

            if (choose == JOptionPane.YES_OPTION){
                database_.execute(
                    "UPDATE user SET message = ?, expire = ? WHERE name = ?;", "", "", currentUser.getUsername()
                );

                User newDataUser = new User(currentUser.getUsername(), currentUser.getPassword(), "", "");
                new Dashboard(newDataUser);
                this.dispose();
            }
            else{
                new Dashboard(currentUser);
                this.dispose();
            }
        }
//        else if (e.getSource() == deleteBtn) {
//            int row = userTable.getSelectedRow();
//            if (row == -1) {
//                return;
//            }
//
//            String username = usernameValue.getText();
//            if (currentUser.getUsername().equals(username)) {
//                JOptionPane.showMessageDialog(null, "Cannot delete yourself", "ERROR", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // DefaultTableModel == TableModel
//            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
//            model.removeRow(row);
//
//            rowValue.setText("");
//            usernameValue.setText("");
//            emailValue.setText("");
//
//            Main.USER_HANDLER.deleteUser(username);
//        }
    }
}
