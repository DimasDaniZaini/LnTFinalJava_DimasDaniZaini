package Element;

import Database.database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame implements ActionListener {

    // Field
    public JTextField usernameField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();

    // Btn
    public JButton registerBtn;
    public JButton loginLabel;

    Database.database database = new database();

    public Register() {

        // Connecting to database
        database.connect();

        setTitle("Register");

        setSize(600, 700);
        setResizable(false);
        setLocationRelativeTo(null); // Pop up di tengah

        buildFFrame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void buildFFrame(){
        int centerMargin = 40;

        // Label Text
        JLabel titleLabel = new JLabel("Register Menu", JLabel.CENTER);
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel note = new JLabel("Note :");
        JLabel usernameRules = new JLabel("1. Username must have >= 3 characters and <= 16 characters");
        JLabel passwordRules1 = new JLabel("2. Password must have >= 8 characters and <= 40 characters");
        JLabel passwordRules2 = new JLabel("3. Password must contain a combination of letters and numbers");

        // Panel
        JPanel centerPanel = new JPanel(new GridLayout(2,2, -10, 20)); // Defaultnya FlowLayout
        JPanel rulesPanel = new JPanel(new GridLayout(2, 1));
        JPanel bottomPanel = new JPanel(new GridLayout(6, 1)); // Atur row & colum
        JPanel buttonPanel = new JPanel(); // Pakai flow layout, udah default

        // Panel Customisation
        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, centerMargin, centerMargin, centerMargin
        )); // Atur Margin


        // Bottom
        registerBtn = new JButton("Register");
        loginLabel = new JButton("Have an Account? Log In Here!");

        registerBtn.setPreferredSize(new Dimension(150, 50));

        // Bottom Customisation
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0, centerMargin, centerMargin, centerMargin
        ));

        // Label Customise
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        registerBtn.setFont(new Font("Arial", Font.BOLD, 16));
        note.setFont(new Font("Arial", Font.BOLD, 16));
        usernameRules.setFont(new Font("Arial", Font.ITALIC, 14));
        passwordRules1.setFont(new Font("Arial", Font.ITALIC, 14));
        passwordRules2.setFont(new Font("Arial", Font.ITALIC, 14));
        loginLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, 0, 0, 0
        )); // Atur matgin

        // Button Customise
        loginLabel.setBorder(null); // Biar bordernya ilang
        loginLabel.setContentAreaFilled(false); // Hilangin background button
        registerBtn.setBorder(null);
        registerBtn.setBackground(Color.white);

        // Field Customise
        usernameField.setBorder(null);
        passwordField.setBorder(null);

        // Rule Panel
        rulesPanel.add(usernameRules);

        // Center Panel
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        // Button Panel
        buttonPanel.add(registerBtn);

        // Bottom Panel
        bottomPanel.add(note);
        bottomPanel.add(usernameRules);
        bottomPanel.add(passwordRules1);
        bottomPanel.add(passwordRules2);
        bottomPanel.add(buttonPanel);
        bottomPanel.add(loginLabel);

        // Add to GUI
        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Biar kalao dipencet, maka ...
        registerBtn.addActionListener(this);
        loginLabel.addActionListener(this);
    }



    public static void main(String[] args) {
        new Register();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Kalau button login ditekan, maka ...
        if (e.getSource() == registerBtn){
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // Validasi, kalo kosong, muncul pesan eror
            if (username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(
                        this, "Username or Password cannot be empty!",
                        "Eror Register",JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Validasi username
            if (username.length() < 3 || username.length() > 16){
                JOptionPane.showMessageDialog(
                        this, "Username is invalid!",
                        "Eror Register",JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Validasi password 1
            if (password.length() < 8 || password.length() > 40){
                JOptionPane.showMessageDialog(
                        this, "Password is invalid!",
                        "Eror Register",JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // Validasi password 2 (bingung cara kombinasiinnya, jd gw cmn ngecek apakah cuman ada number / letter)
            if (password.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$")){
                    JOptionPane.showMessageDialog(
                            this, "Password is invalid!",
                            "Eror Register",JOptionPane.ERROR_MESSAGE
                    );

                    return;
            }

            // Import to database
            database.execute(
                    "INSERT INTO user " + "(id, name, password) VALUES  (?, ?, ?);",
                    1, username, password
            );

            // Sucessfully Register
            JOptionPane.showMessageDialog(
                    this, "Successfully Register!",
                    "Successfully Register",JOptionPane.INFORMATION_MESSAGE
            );

            new Login();
            this.dispose();

        }

        if (e.getSource() == loginLabel){
            new Login();
            this.dispose();
        }
    }

}
