package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Homepage extends JFrame {
    private final JTextField searchField;

    public Homepage() {
        // Set frame properties
        setTitle("Homepage");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Prevent resizing

        // Gradient background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(135, 206, 250); // Light blue
                Color color2 = new Color(0, 123, 255);  // Deep blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setLayout(new BorderLayout());
        add(gradientPanel);

        // Welcome label
        JLabel lblWelcome = new JLabel("Student Management System", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 22));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBorder(new EmptyBorder(20, 10, 20, 10));
        gradientPanel.add(lblWelcome, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        searchPanel.setOpaque(false); // Transparent background
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        JButton btnSearch = createStyledButton("Search Students");
        btnSearch.addActionListener(e -> searchStudents(searchField.getText()));
        searchPanel.add(searchField);
        searchPanel.add(btnSearch);
        gradientPanel.add(searchPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnManageStudents = createStyledButton("Manage Students");
        JButton btnLogout = createStyledButton("Logout");

        btnManageStudents.addActionListener(e -> manageStudents());
        btnLogout.addActionListener(e -> logout());

        buttonPanel.add(btnManageStudents);
        buttonPanel.add(Box.createVerticalStrut(15)); // Spacing between buttons
        buttonPanel.add(btnLogout);

        // Center the button panel
        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new GridBagLayout());
        buttonContainer.add(buttonPanel);

        gradientPanel.add(buttonContainer, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method for creating a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false); // Transparent content area
        button.setOpaque(true); // Ensures custom background is visible

        // Add rounded corners
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 200), 2));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(28, 151, 234)); // Lighter blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 123, 255)); // Original color
            }
        });

        return button;
    }

    // Method for managing students (Add, Update, View, Delete)
    private void manageStudents() {
        new ManageStudentsForm(); // Open the new form
        this.dispose(); // Close the current window
    }

    // Method to search students
    private void searchStudents(String query) {
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String url = "jdbc:mysql://localhost:3306/student_management";
        String user = "root";
        String password = "root";

        String sql = "SELECT * FROM students WHERE name LIKE ? OR roll_no LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String studentInfo = "Roll No: " + rs.getString("roll_no") + "\n" +
                        "Name: " + rs.getString("name") + "\n" +
                        "Email: " + rs.getString("email") + "\n" +
                        "Phone: " + rs.getString("phone") + "\n" +
                        "Branch: " + rs.getString("branch");
                JOptionPane.showMessageDialog(this, studentInfo, "Student Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No student found with the given query.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching for student: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method for logging out
    private void logout() {
        JOptionPane.showMessageDialog(this, "Logged Out!");
        new login();
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Homepage::new);
    }
}
