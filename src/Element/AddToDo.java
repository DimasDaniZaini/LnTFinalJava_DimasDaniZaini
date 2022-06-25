package Element;

import Database.Result;
import Database.User;
import Database.database;
import org.w3c.dom.CDATASection;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class AddToDo extends JFrame implements ActionListener {

    // Field
    public JTextField toDoField = new JTextField();
    public JTextField expireFiled = new JTextField();

    // Btn
    public JButton saveBtn;
    private final User currentUser;


    database database_ = new database();

    public AddToDo(User user) {
        this.currentUser = user;

        setTitle("Add To Do");

        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Pop up di tengah

        buildFFrame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void buildFFrame() {
        int centerMargin = 40;

        // Label Text
        JLabel titleLabel = new JLabel("Add To Do", JLabel.CENTER);
        JLabel textLabel = new JLabel("Input your text below!");
        JLabel expireLabel = new JLabel("Expired time : dd/mm/yyyy");

        // Panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel centerPanel = new JPanel(new GridLayout(3,1, -10, 20)); // Defaultnya FlowLayout
        JPanel buttonPanel = new JPanel(); // Pakai flow layout, udah default

        // Panel Customisation
        topPanel.setBorder(BorderFactory.createEmptyBorder(
                10, centerMargin * 2, 0, centerMargin * 2
        )); // Atur Margin

        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                0, centerMargin * 2, centerMargin, centerMargin * 2
        )); // Atur Margin

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
                0, centerMargin * 2, centerMargin, centerMargin * 2
        )); // Atur Margin

        // Bottom
        saveBtn = new JButton("Add to my To Do");
        saveBtn.setPreferredSize(new Dimension(150, 50));


        // Bottom Customisation
//        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
//                0, centerMargin, centerMargin, centerMargin
//        ));

        // Label Customise
        titleLabel.setFont(new Font(null, Font.BOLD, 24));
        textLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        expireLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        toDoField.setFont(new Font("Arial", Font.ITALIC, 14));
        expireFiled.setFont(new Font("Arial", Font.ITALIC, 14));
        saveBtn.setFont(new Font("Arial", Font.ITALIC, 16));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, 0, 0, 0
        )); // Atur matgin

        // Button Customise
        saveBtn.setBorder(null); // Biar bordernya ilang
        saveBtn.setBackground(Color.white);

        // Field Customise
        toDoField.setBorder(null);

        // Top Panel
        topPanel.add(titleLabel);
        topPanel.add(textLabel);

        // Center Panel
        centerPanel.add(toDoField);
        centerPanel.add(expireLabel);
        centerPanel.add(expireFiled);

        // Bottom Panel
        buttonPanel.add(saveBtn);

        // Add to GUI
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Biar kalao dipencet, maka ...
        saveBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Kalo milih button login
        if (e.getSource() == saveBtn){

            String newToDo = toDoField.getText();
            String expire = expireFiled.getText();

            // Cek datanya ada ga di database

            database_.execute(
                    "UPDATE user SET message = ? WHERE name = ?;", newToDo, currentUser.getUsername()

            );


            JOptionPane.showMessageDialog(
                    this, "Successfully Added!",
                    "Successfully Register",JOptionPane.INFORMATION_MESSAGE
            );

            User newDataUser = new User(currentUser.getUsername(), currentUser.getPassword(), newToDo, expire);
            new Dashboard(newDataUser);
            this.dispose();

        }

    }

}
