import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// Main frame to hold and switch between login and registration panels
public class LoginFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginForm loginPanel;
    private RegistrationForm registrationPanel;

    // A simple in-memory "database" to store user credentials (for demonstration)
    // In a real application, this would be connected to a database.
    private Map<String, String> registeredUsers = new HashMap<>();
    private Map<String, String> registeredStudents = new HashMap<>(); // Store student details

    public LoginFrame() {
        setTitle("Student Resource Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize panels and pass the main frame reference to allow switching
        loginPanel = new LoginForm(this);
        registrationPanel = new RegistrationForm(this);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registrationPanel, "Register");

        add(mainPanel);

        // Show the login panel initially
        cardLayout.show(mainPanel, "Login");
    }

    // Method to switch to the registration panel
    public void showRegistrationPanel() {
        cardLayout.show(mainPanel, "Register");
    }

    // Method to switch back to the login panel
    public void showLoginPanel() {
        cardLayout.show(mainPanel, "Login");
    }

    // Method to register a new user
    public boolean registerUser(String studentId, String studentName, String username, String password) {
        if (registeredUsers.containsKey(username)) {
            return false; // Username already exists
        }
        registeredUsers.put(username, password);
        registeredStudents.put(username, "ID:" + studentId + ", Name:" + studentName); // Store student details linked to username
        return true;
    }

    // Method to authenticate a user
    public boolean authenticateUser(String username, String password) {
        return registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

// Login Form Panel
class LoginForm extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerLinkButton;
    private LoginFrame parentFrame;

    public LoginForm(LoginFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout()); // Using GridBagLayout for better control
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally

        // Title
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(titleLabel, gbc);

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Username:"), gbc);

        // Username Field
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        // Password Field
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(60, 179, 113)); // MediumSeaGreen
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Please enter both username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (parentFrame.authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(parentFrame, "Login Successful! Welcome, " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // In a real app, you would navigate to the main application dashboard here.
                    // For this example, we'll just show a success message.
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        add(loginButton, gbc);

        // Register Link Button
        registerLinkButton = new JButton("Don't have an account? Register here.");
        registerLinkButton.setBorderPainted(false);
        registerLinkButton.setContentAreaFilled(false);
        registerLinkButton.setFocusPainted(false);
        registerLinkButton.setForeground(Color.BLUE.darker()); // Darker blue for link appearance
        registerLinkButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showRegistrationPanel();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(registerLinkButton, gbc);
    }
}

// Registration Form Panel
class RegistrationForm extends JPanel {

    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton backToLoginButton;
    private LoginFrame parentFrame;

    public RegistrationForm(LoginFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Register New Student", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Student ID Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Student ID:"), gbc);

        // Student ID Field
        studentIdField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(studentIdField, gbc);

        // Student Name Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Student Name:"), gbc);

        // Student Name Field
        studentNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(studentNameField, gbc);

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Username:"), gbc);

        // Username Field
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Password:"), gbc);

        // Password Field
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(passwordField, gbc);

        // Confirm Password Label
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Confirm Password:"), gbc);

        // Confirm Password Field
        confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(confirmPasswordField, gbc);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(70, 130, 180)); // SteelBlue
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setOpaque(true);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String studentName = studentNameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (studentId.isEmpty() || studentName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(parentFrame, "Passwords do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (parentFrame.registerUser(studentId, studentName, username, password)) {
                    JOptionPane.showMessageDialog(parentFrame, "Registration Successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parentFrame.showLoginPanel(); // Go back to login after successful registration
                    // Clear fields after successful registration
                    studentIdField.setText("");
                    studentNameField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Username already exists. Please choose a different one.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(registerButton, gbc);

        // Back to Login Button
        backToLoginButton = new JButton("Back to Login");
        backToLoginButton.setBorderPainted(false);
        backToLoginButton.setContentAreaFilled(false);
        backToLoginButton.setFocusPainted(false);
        backToLoginButton.setForeground(Color.BLUE.darker());
        backToLoginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.showLoginPanel();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(backToLoginButton, gbc);
    }
}
