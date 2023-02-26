package BatchProcessingGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupPage extends JFrame implements ActionListener {

    private JLabel headerLabel, nameLabel, emailLabel, passwordLabel, confirmPasswordLabel, alreadyHaveAccountLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signupButton, loginButton;

    public SignupPage() {

        setTitle("Signup Page");

        headerLabel = new JLabel("Create an Account");
        nameLabel = new JLabel("Name: ");
        emailLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        confirmPasswordLabel = new JLabel("Confirm Password: ");
        alreadyHaveAccountLabel = new JLabel("Already have an account?");

        nameField = new JTextField(20);
        emailField = new JTextField(20);

        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        signupButton = new JButton("Signup");
        loginButton = new JButton("Login");

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(headerLabel);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);

        JPanel signupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signupPanel.add(signupButton);

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel.add(alreadyHaveAccountLabel);
        loginPanel.add(loginButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(headerPanel);
        mainPanel.add(namePanel);
        mainPanel.add(emailPanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(confirmPasswordPanel);
        mainPanel.add(signupPanel);
        mainPanel.add(loginPanel);

        add(mainPanel);

        signupButton.addActionListener(this);
        loginButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            // Perform Signup action here
        } else if (e.getSource() == loginButton) {
            // Open Login page here
        }
    }

    public static void main(String[] args) {
        new SignupPage();
    }
}
