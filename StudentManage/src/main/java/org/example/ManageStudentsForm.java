package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageStudentsForm extends JFrame {
    private final JTextField txtRollNo, txtName, txtEmail, txtPhone, txtBranch, txtUsername;
    private final JPasswordField txtPassword, txtConfirmPassword; // Use JPasswordField for password inputs

    public ManageStudentsForm() {
        // Set frame properties
        setTitle("Manage Students");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridBagLayout()); // Use GridBagLayout for better control

        // Custom font and colors
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Color buttonColor = new Color(0, 123, 255);
        Color backgroundColor = new Color(245, 245, 245);

        // Set the background color
        getContentPane().setBackground(backgroundColor);

        // Initialize components
        JLabel lblRollNo = new JLabel("Roll No:");
        lblRollNo.setFont(labelFont);
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(labelFont);
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(labelFont);
        JLabel lblBranch = new JLabel("Branch:");
        lblBranch.setFont(labelFont);
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(labelFont);
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(labelFont);
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(labelFont);

        // Initialize text fields and password fields
        txtRollNo = createStyledTextField();
        txtName = createStyledTextField();
        txtEmail = createStyledTextField();
        txtPhone = createStyledTextField();
        txtBranch = createStyledTextField();
        txtUsername = createStyledTextField();
        txtPassword = createStyledPasswordField();
        txtConfirmPassword = createStyledPasswordField();

        // Initialize buttons
        JButton btnAdd = createStyledButton("Add Student");
        JButton btnEdit = createStyledButton("Edit Student");
        JButton btnDelete = createStyledButton("Delete Student");
        // Added Back button
        JButton btnBack = createStyledButton("Back to Homepage"); // Back button

        // Action listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToHomePage(); // Go back to homepage
            }
        });

        // Layout settings for components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblRollNo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtRollNo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblBranch, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtBranch, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        add(lblConfirmPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        add(txtConfirmPassword, gbc);

        // Add buttons
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnAdd, gbc);

        gbc.gridy = 9;
        add(btnEdit, gbc);

        gbc.gridy = 10;
        add(btnDelete, gbc);

        // Add the Back button
        gbc.gridy = 11;
        add(btnBack, gbc);

        // Make the frame visible
        setVisible(true);
    }

    // Method to create a styled text field
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    // Method to create a styled password field
    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        return passwordField;
    }

    // Method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    // Method to add a student (connect to database)
    private void addStudent() {
        String rollNo = txtRollNo.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String branch = txtBranch.getText();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (password.equals(confirmPassword)) {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO students (roll_no, name, email, phone, branch, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, rollNo);
                    stmt.setString(2, name);
                    stmt.setString(3, email);
                    stmt.setString(4, phone);
                    stmt.setString(5, branch);
                    stmt.setString(6, username);
                    stmt.setString(7, password);  // Password should be hashed in a real application

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Student added successfully!");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to edit a student's details
    private void editStudent() {
        String rollNo = txtRollNo.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String branch = txtBranch.getText();
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (password.equals(confirmPassword)) {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "UPDATE students SET name = ?, email = ?, phone = ?, branch = ?, username = ?, password = ? WHERE roll_no = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, name);
                    stmt.setString(2, email);
                    stmt.setString(3, phone);
                    stmt.setString(4, branch);
                    stmt.setString(5, username);
                    stmt.setString(6, password);  // Password should be hashed in a real application
                    stmt.setString(7, rollNo);

                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Student updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found.");
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating student: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete a student's record
    private void deleteStudent() {
        String rollNo = txtRollNo.getText();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM students WHERE roll_no = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, rollNo);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage());
        }
    }

    // Method to go back to the homepage
    private void backToHomePage() {
        dispose(); // Close the current form
        new Homepage(); // Open the homepage form
    }

    public static void main(String[] args) {
        new ManageStudentsForm();
    }
}
