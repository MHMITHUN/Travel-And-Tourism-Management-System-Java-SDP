package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PackageReceipt extends JFrame implements ActionListener {
    JPanel headerpanel;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JButton b1, b2;
    String username;
    int bookingId;

    public PackageReceipt(String username, int bookingId) {
        this.username = username;
        this.bookingId = bookingId;
        
        setTitle("Package Booking Receipt");
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        headerpanel = new JPanel();
        headerpanel.setBackground(new Color(32, 178, 170));
        headerpanel.setBounds(0, 0, 800, 50);
        add(headerpanel);
        headerpanel.setLayout(null);

        l1 = new JLabel("Package Booking Receipt");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(0, 2, 800, 50);
        headerpanel.add(l1);

        // Labels for receipt details
        l2 = new JLabel(""); // Place Name
        l2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l2.setBounds(60, 80, 700, 30);
        add(l2);

        l3 = new JLabel(""); // Customer Name
        l3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l3.setBounds(60, 120, 700, 30);
        add(l3);

        l4 = new JLabel(""); // Username
        l4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l4.setBounds(60, 160, 700, 30);
        add(l4);

        l5 = new JLabel(""); // Number of Persons
        l5.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l5.setBounds(60, 200, 700, 30);
        add(l5);

        l6 = new JLabel(""); // Start Date
        l6.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l6.setBounds(60, 240, 700, 30);
        add(l6);

        l7 = new JLabel(""); // End Date
        l7.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l7.setBounds(60, 280, 700, 30);
        add(l7);

        l8 = new JLabel(""); // Total Price
        l8.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l8.setBounds(60, 320, 700, 30);
        add(l8);

        l9 = new JLabel(""); // Booking Date
        l9.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l9.setBounds(60, 360, 700, 30);
        add(l9);

        // Buttons
        b1 = new JButton("Save Receipt");
        b1.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(32, 178, 170));
        b1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b1.addActionListener(this);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBounds(320, 480, 160, 40);
        b1.setFocusable(false);
        add(b1);

        loadBookingDetails();
        setVisible(true);
    }

    private void loadBookingDetails() {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM bookpackage WHERE username=? AND ROWID=?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, username);
            ps.setInt(2, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                l2.setText("Place Name: " + rs.getString("place"));
                l3.setText("Customer Name: " + rs.getString("name"));
                l4.setText("Username: " + rs.getString("username"));
                l5.setText("Number of Persons: " + rs.getString("persons"));
                l6.setText("Start Date: " + rs.getString("date"));
                l7.setText("End Date: " + rs.getString("end_date"));
                l8.setText("Total Price: ₹" + rs.getString("totalprice"));
                
                // Add current date as booking date
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                l9.setText("Booking Date: " + dateFormat.format(new Date()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveReceipt() {
        try {
            String fileName = "Package_Receipt_" + username + "_" + System.currentTimeMillis() + ".txt";
            String userHome = System.getProperty("user.home");
            String downloadsPath = userHome + "\\Downloads\\";
            File file = new File(downloadsPath + fileName);

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("============= PACKAGE BOOKING RECEIPT =============\n\n");
            bw.write(l2.getText() + "\n");
            bw.write(l3.getText() + "\n");
            bw.write(l4.getText() + "\n");
            bw.write(l5.getText() + "\n");
            bw.write(l6.getText() + "\n");
            bw.write(l7.getText() + "\n");
            bw.write(l8.getText() + "\n");
            bw.write(l9.getText() + "\n");
            bw.write("\n=================================================\n");
            bw.write("Thank you for booking with us!\n");
            bw.write("For any queries, please contact our customer service.\n");
            bw.write("=================================================");

            bw.close();
            fw.close();

            JOptionPane.showMessageDialog(null, "Receipt saved successfully in Downloads folder!");
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving receipt!");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            saveReceipt();
        }
    }
}
