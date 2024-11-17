package org.example;

//package org.example.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JFrame implements ActionListener {
    private final JTextField txtRoll;
    private final JTextField txtName;
    private final JTextField txtEmail;
    private final JTextField txtPhone;
    private final JTextField txtBranch;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JPasswordField txtConfirmPassword;
    private final JButton btnSubmit;
    private final JButton btnBack;

    public Registration() {
        // Set frame properties
        setTitle("Registration Page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allows going back to Login without closing everything
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null); // Center the window

        // Initialize components
        JLabel lblRoll = new JLabel("Roll Number:");
        JLabel lblName = new JLabel("Name:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhone = new JLabel("Phone:");
        JLabel lblBranch = new JLabel("Branch:");
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");

        txtRoll = new JTextField(15);
        txtName = new JTextField(15);
        txtEmail = new JTextField(15);
        txtPhone = new JTextField(15);
        txtBranch = new JTextField(15);
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtConfirmPassword = new JPasswordField(15);

        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");

        // Add action listeners
        btnSubmit.addActionListener(this);
        btnBack.addActionListener(this);

        // Layout settings
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblRoll, gbc);
        gbc.gridx = 1;
        add(txtRoll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblName, gbc);
        gbc.gridx = 1;
        add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEmail, gbc);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPhone, gbc);
        gbc.gridx = 1;
        add(txtPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblBranch, gbc);
        gbc.gridx = 1;
        add(txtBranch, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblUsername, gbc);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblPassword, gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblConfirmPassword, gbc);
        gbc.gridx = 1;
        add(txtConfirmPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSubmit, gbc);

        gbc.gridy = 9;
        add(btnBack, gbc);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String roll = txtRoll.getText();
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String branch = txtBranch.getText();
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            // Validate input
            if (roll.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || branch.isEmpty() ||
                    username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are mandatory.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                // Here, you can add code to save user data to a database or file
                dispose(); // Close the registration window
            }
        } else if (e.getSource() == btnBack) {
            dispose(); // Close registration window
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Registration::new);
    }
}

