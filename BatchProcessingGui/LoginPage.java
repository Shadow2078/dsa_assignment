package BatchProcessingGui;

import BatchProcessingGui.SignupPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel titleLabel, usernameLabel, passwordLabel, newAccountLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);

        titleLabel = new JLabel("Login to Your Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(80, 20, 250, 30);
        add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 80, 100, 20);
        add(usernameLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 20);
        add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 80, 150, 20);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 120, 150, 20);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 180, 80, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(200, 180, 80, 30);
        signupButton.addActionListener(this);
        add(signupButton);

        newAccountLabel = new JLabel("Don't have an account?");
        newAccountLabel.setBounds(210, 450, 150, 20);
        add(newAccountLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            // Perform login authentication logic here
        } else if (e.getSource() == signupButton) {
            dispose();
            SignupPage signupPage = new SignupPage();
        }
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
    }
}
