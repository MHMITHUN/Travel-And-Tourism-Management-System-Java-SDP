package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.sql.*;

public class AddHotel extends JFrame implements ActionListener {

    // Panel and components //real one
    private JPanel headerPanel;
    private JTextField t1, t2, t3, t4, t5;
    private JLabel l1, l2, l3, l4, l5, l6, l7, filename;
    private JButton b1, b2, b3;
    private File file;
    private HotelPanel hp;
    private HotelDetails hd;
    private String originalName; // To store the original hotel name
    private byte[] photo = null;

    /**
     * Default constructor for adding a new hotel.
     */
    public AddHotel() {
        // Frame properties
        setTitle("Add Hotel");
        setSize(750, 612);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#f8f9fa")); // Light background
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Header panel
        headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#343a40")); // Dark header
        headerPanel.setBounds(0, 0, 750, 50);
        headerPanel.setLayout(null);
        add(headerPanel);

        l1 = new JLabel("Add Hotel");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(0, 5, 750, 40);
        headerPanel.add(l1);

        // Labels
        l2 = createLabel("Hotel Name:", 60, 80);
        l4 = createLabel("City/State Name:", 60, 130);
        l5 = createLabel("Hotel Cost AC:", 60, 180); // Updated label
        l6 = createLabel("Food Cost Per Day:", 60, 230);
        l7 = createLabel("Hotel Cost Non-AC:", 60, 280); // Updated label

        // Text fields
        t1 = createTextField(270, 80);
        t2 = createTextField(270, 130);
        t3 = createTextField(270, 180);
        t4 = createTextField(270, 230);
        t5 = createTextField(270, 280);

        // Image preview
        l3 = new JLabel();
        l3.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        l3.setBounds(60, 335, 220, 220);
        Image placeholderImage = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/hotelicon.jpeg"))
                .getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        l3.setIcon(new ImageIcon(placeholderImage));
        add(l3);

        // Buttons
        b1 = createButton("Choose File", 290, 425, "#6c757d"); // Grey button
        b2 = createButton("Add", 578, 525, "#28a745"); // Green button
        b3 = createButton("Update", 578, 525, "#007bff"); // Blue button
        b3.setVisible(false); // Initially hidden for add mode

        // Filename label
        filename = new JLabel("No file chosen");
        filename.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        filename.setBounds(290, 470, 400, 32);
        add(filename);
    }

    public AddHotel(HotelPanel hp) {
        this();
        this.hp = hp;
    }

    public AddHotel(HotelDetails hd, String name) {
        this();
        this.hd = hd;
        this.originalName = name; // Store the original name
        l1.setText("Edit Hotel");
        b2.setVisible(false); // Hide Add button
        b3.setVisible(true);  // Show Update button

        try {
            Conn conn = new Conn();
            String sql = "SELECT * FROM hotel WHERE name=?";
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Make the hotel name field editable
                // Removed t1.setEditable(false);
                t1.setBackground(Color.WHITE);
                t1.setText(rs.getString("name")); // Hotel Name
                t2.setText(rs.getString("state")); // City/State
                t3.setText(rs.getString("ac")); // Hotel Cost AC
                t4.setText(rs.getString("food")); // Food Cost
                t5.setText(rs.getString("hotelcost")); // Hotel Cost Non-AC

                byte[] photoBytes = rs.getBytes("image");
                if (photoBytes != null && photoBytes.length > 0) {
                    Image existingImage = new ImageIcon(photoBytes).getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
                    l3.setIcon(new ImageIcon(existingImage));
                    this.photo = photoBytes; // Retain existing image
                    this.filename.setText("Existing Image");
                } else {
                    this.filename.setText("No Image Available");
                }
            }
            rs.close();
            ps.close();
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setBounds(x, y, 200, 40);
        add(label);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textField.setBounds(x, y, 380, 40);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        add(textField);
        return textField;
    }


    private JButton createButton(String text, int x, int y, String colorHex) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setBackground(Color.decode(colorHex));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBounds(x, y, 140, 38);
        button.setFocusable(false);
        button.addActionListener(this); // Ensure buttons remain functional
        add(button);
        return button;
    }

    /**
     * Action handler for button clicks.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Choose File button
            chooseFile();
        } else if (e.getSource() == b2) { // Add button
            addHotel();
        } else if (e.getSource() == b3) { // Update button
            updateHotel();
        }
    }

    /**
     * Method to handle file selection and image preview.
     */
    private void chooseFile() {
        FileDialog fd = new FileDialog(this, "Choose a Hotel Image", FileDialog.LOAD);
        fd.setFile("*.jpeg;*.jpg;*.png");
        fd.setVisible(true);
        String selectedFile = fd.getFile();
        if (selectedFile != null) {
            file = new File(fd.getDirectory() + selectedFile);
            try {
                Image image = ImageIO.read(file).getScaledInstance(220, 220, Image.SCALE_SMOOTH);
                l3.setIcon(new ImageIcon(image));
                this.filename.setText(file.getName());

                // Read the image file into byte array
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int readNum;
                while ((readNum = fis.read(buf)) != -1) {
                    bos.write(buf, 0, readNum);
                }
                photo = bos.toByteArray(); // Update the photo field with the new image data
                fis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to load image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Method to handle adding a new hotel to the database.
     */
    private void addHotel() {
        try {
            // Validate inputs
            if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() || t3.getText().trim().isEmpty()
                    || t4.getText().trim().isEmpty() || t5.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Ensure an image is selected
            if (photo == null) {
                JOptionPane.showMessageDialog(this, "Please choose an image for the hotel.", "Image Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conn conn = new Conn();
            String sql = "INSERT INTO hotel (name, state, ac, food, hotelcost, image) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ps.setString(1, t1.getText().trim());
            ps.setString(2, t2.getText().trim());
            ps.setString(3, t3.getText().trim()); // Hotel Cost AC
            ps.setString(4, t4.getText().trim());
            ps.setString(5, t5.getText().trim()); // Hotel Cost Non-AC
            ps.setBytes(6, photo);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Hotel Added Successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            if (hp != null) {
                hp.createtablemodel(); // Refresh the hotel table in HotelPanel
            }

            ps.close();
            conn.c.close();
            this.dispose(); // Close the AddHotel window
        } catch (SQLIntegrityConstraintViolationException sicve) {
            JOptionPane.showMessageDialog(this, "Hotel with this name already exists.", "Duplicate Entry",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding the hotel.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to handle updating an existing hotel in the database.
     */
    private void updateHotel() {
        try {
            // Validate inputs
            if (t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() || t3.getText().trim().isEmpty()
                    || t4.getText().trim().isEmpty() || t5.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conn conn = new Conn();
            String sql = "UPDATE hotel SET name=?, state=?, ac=?, food=?, hotelcost=?, image=? WHERE name=?";
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ps.setString(1, t1.getText().trim()); // New hotel name
            ps.setString(2, t2.getText().trim());
            ps.setString(3, t3.getText().trim()); // Hotel Cost AC
            ps.setString(4, t4.getText().trim());
            ps.setString(5, t5.getText().trim()); // Hotel Cost Non-AC

            if (photo != null) {
                ps.setBytes(6, photo); // Use existing or new image data
            } else {
                // If photo is null, set it to the existing image from the database
                ps.setNull(6, Types.BLOB);
            }

            ps.setString(7, originalName); // Use original name for WHERE clause
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Hotel Updated Successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            if (hd != null) {
                hd.display(); // Refresh the hotel details in HotelDetails
            }

            ps.close();
            conn.c.close();
            this.dispose(); // Close the AddHotel window
        } catch (SQLIntegrityConstraintViolationException sicve) {
            JOptionPane.showMessageDialog(this, "Hotel with the new name already exists.", "Duplicate Entry",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating the hotel.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Main method for testing the AddHotel class independently.
     */
    public static void main(String[] args) {
        // For testing purposes: Launch the AddHotel
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddHotel().setVisible(true);
            }
        });
    }
}
