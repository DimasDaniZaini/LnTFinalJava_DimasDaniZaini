package Element;

import Database.Result;
import Database.User;
import Database.database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    // Field
    public JTextField usernameField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();

    // Btn
    public JButton loginBtn;
    public JButton registerLabel;

     database database_ = new database();

    public Login() {
        setTitle("Register");

        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null); // Pop up di tengah

        buildFFrame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void buildFFrame(){
        int centerMargin = 40;

        // Label Text
        JLabel titleLabel = new JLabel("Login Menu", JLabel.CENTER);
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        // Panel
        JPanel centerPanel = new JPanel(new GridLayout(2,2, -10, 20)); // Defaultnya FlowLayout
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1)); // Atur row & colum
        JPanel buttonPanel = new JPanel(); // Pakai flow layout, udah default

        // Panel Customisation
        centerPanel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, centerMargin * 2, centerMargin, centerMargin * 2
        )); // Atur Margin

        // Bottom
        loginBtn = new JButton("Login");
        registerLabel = new JButton("Don't Have an Account yet? Sign Up Here!");

        loginBtn.setPreferredSize(new Dimension(150, 50));


        // Bottom Customisation
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(
                0, centerMargin, centerMargin, centerMargin
        ));

        // Label Customise
        titleLabel.setFont(new Font(null, Font.BOLD, 24));
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        registerLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        titleLabel.setBorder(BorderFactory.createEmptyBorder(
                centerMargin, 0, 0, 0
        )); // Atur matgin

        // Button Customise
        registerLabel.setBorder(null); // Biar bordernya ilang
        registerLabel.setContentAreaFilled(false); // Hilangin background button
        loginBtn.setBorder(null);
        loginBtn.setBackground(Color.white);

        // Field Customise
        usernameField.setBorder(null);
        passwordField.setBorder(null);

        // Center Panel
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);

        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        // Bottom Panel
        buttonPanel.add(loginBtn);
        bottomPanel.add(buttonPanel);
        bottomPanel.add(registerLabel);

        // Add to GUI
        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Biar kalao dipencet, maka ...
        loginBtn.addActionListener(this);
        registerLabel.addActionListener(this);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Kalo milih button login
        if (e.getSource() == loginBtn){
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

//            // Cek datanya ada ga di database
            try (Result res = database_.getResult("SELECT * FROM user WHERE name = ? AND password = ?;", username, password)){
                ResultSet set = res.getResultSet();
                // Kalau ketemu, maka caw
                if (set.next()){
                    JOptionPane.showMessageDialog(
                            this, "Successfully Login!",
                            "Successfully Register",JOptionPane.INFORMATION_MESSAGE
                    );

                    String message = set.getNString("message");
                    String expire =  set.getNString("expire");

                    User user = new User(username, password, message, expire);
                    new Dashboard(user);
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(
                            this, "Username or Password is invalid!",
                            "Eror Login",JOptionPane.ERROR_MESSAGE
                    );

                }
            }catch (Exception error){
                error.printStackTrace();
            }
        }

        // Kalo milih register
        if (e.getSource() == registerLabel){
            new Register();
            this.dispose();
        }
    }


}
