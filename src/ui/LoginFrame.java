package ui;

import dao.UserDAO;
import util.LanguageManager;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JComboBox<String> languageBox;

    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel roleLabel;
    private JLabel languageLabel;

    private JButton loginButton;
    private JButton exitButton;

    private UserDAO userDAO = new UserDAO();

    public LoginFrame() {

        setTitle(LanguageManager.get("login_title"));
        setSize(450, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        usernameLabel = new JLabel(LanguageManager.get("username"));
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        passwordLabel = new JLabel(LanguageManager.get("password"));
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        roleLabel = new JLabel(LanguageManager.get("role"));
        panel.add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Admin", "Doctor", "Receptionist"});
        panel.add(roleBox);

        languageLabel = new JLabel("Language:");
        panel.add(languageLabel);

        languageBox = new JComboBox<>(new String[]{"EN", "TR", "AZ", "RU"});
        panel.add(languageBox);

        loginButton = new JButton(LanguageManager.get("login"));
        exitButton = new JButton(LanguageManager.get("exit"));

        panel.add(loginButton);
        panel.add(exitButton);

        add(panel);

        loginButton.addActionListener(e -> login());
        exitButton.addActionListener(e -> System.exit(0));

        languageBox.addActionListener(e -> {
            String selectedLanguage = languageBox.getSelectedItem().toString();
            LanguageManager.setLanguage(selectedLanguage);
            updateLanguage();
        });
    }

    private void updateLanguage() {

        setTitle(LanguageManager.get("login_title"));
        usernameLabel.setText(LanguageManager.get("username"));
        passwordLabel.setText(LanguageManager.get("password"));
        roleLabel.setText(LanguageManager.get("role"));
        loginButton.setText(LanguageManager.get("login"));
        exitButton.setText(LanguageManager.get("exit"));
    }

    private void login() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String role = roleBox.getSelectedItem().toString();

        boolean validLogin = userDAO.login(username, password, role);

        if (validLogin) {

            JOptionPane.showMessageDialog(this, LanguageManager.get("login_success"));

            MainFrame mainFrame = new MainFrame(role);
            mainFrame.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(this, LanguageManager.get("login_error"));
        }
    }
}