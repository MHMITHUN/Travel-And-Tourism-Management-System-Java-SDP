package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class BookPackage extends JFrame implements ActionListener {

    JPanel headerpanel;
    JTextField t1, t2, t3, t4;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JButton b1, b2;
    JDateChooser startDateChooser, endDateChooser;
    JSpinner spinnerPersons;
    String place, user;
    private int numberOfDays; // Number of days extracted from days_nights

    public BookPackage(String place, String user) {
        this.place = place;
        this.user = user;
        setTitle("Book Package");
        setSize(750, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // **Change the default close operation here**
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE

        // Header Panel
        headerpanel = new JPanel();
        headerpanel.setBackground(new Color(32, 178, 170));
        headerpanel.setBounds(0, 0, 750, 50);
        add(headerpanel);
        headerpanel.setLayout(null);

        l1 = new JLabel("Book Package");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(0, 2, 750, 50);
        headerpanel.add(l1);

        // Labels
        l2 = new JLabel("Place Name:");
        l2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l2.setBounds(60, 80, 200, 40);
        add(l2);

        l3 = new JLabel("Customer Name:");
        l3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l3.setBounds(60, 130, 200, 40);
        add(l3);

        l4 = new JLabel("Username:");
        l4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l4.setBounds(60, 180, 200, 40);
        add(l4);

        l5 = new JLabel("Package Price:");
        l5.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l5.setBounds(60, 230, 200, 40);
        add(l5);

        l6 = new JLabel("Total Persons:");
        l6.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l6.setBounds(60, 280, 200, 40);
        add(l6);

        l7 = new JLabel("Start Date:");
        l7.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l7.setBounds(60, 330, 200, 40);
        add(l7);

        l8 = new JLabel("End Date:");
        l8.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l8.setBounds(60, 380, 200, 40);
        add(l8);

        l9 = new JLabel("Total Price:");
        l9.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l9.setBounds(60, 430, 200, 40);
        add(l9);

        // Text Fields
        t1 = new JTextField();
        t1.setForeground(Color.DARK_GRAY);
        t1.setEditable(false);
        t1.setBackground(Color.WHITE);
        t1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t1.setBounds(270, 80, 380, 40);
        add(t1);

        t2 = new JTextField();
        t2.setForeground(Color.DARK_GRAY);
        t2.setEditable(false);
        t2.setBackground(Color.WHITE);
        t2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t2.setBounds(270, 130, 380, 40);
        add(t2);

        t3 = new JTextField();
        t3.setForeground(Color.DARK_GRAY);
        t3.setEditable(false);
        t3.setBackground(Color.WHITE);
        t3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18)); 
        t3.setBounds(270, 180, 380, 40);
        add(t3);

        t4 = new JTextField();
        t4.setForeground(Color.DARK_GRAY);
        t4.setEditable(false);
        t4.setBackground(Color.WHITE);
        t4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t4.setBounds(270, 230, 380, 40);
        add(t4);

        // Spinner for Total Persons
        spinnerPersons = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Min=1, Max=100, Step=1
        spinnerPersons.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        spinnerPersons.setBounds(270, 280, 380, 40);
        JComponent editor = spinnerPersons.getEditor();
        JFormattedTextField textFieldSpinner = ((JSpinner.DefaultEditor) editor).getTextField();
        textFieldSpinner.setHorizontalAlignment(SwingConstants.LEFT); // Align to the start
        add(spinnerPersons);

        // Date Choosers
        startDateChooser = new JDateChooser();
        startDateChooser.setBounds(270, 330, 380, 40);
        add(startDateChooser);

        endDateChooser = new JDateChooser();
        endDateChooser.setBounds(270, 380, 380, 40);
        endDateChooser.setEnabled(false); // Make end date non-editable
        add(endDateChooser);

        // Total Price Label
        l9 = new JLabel("00");
        l9.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l9.setBounds(270, 430, 380, 40);
        add(l9);

        // Buttons
        b1 = new JButton("Book");
        b1.setBorder(new EmptyBorder(0, 0, 0, 0));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(32, 178, 170));
        b1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b1.addActionListener(this);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBounds(520, 485, 160, 40);
        b1.setFocusable(false);
        add(b1);

        b2 = new JButton("Check Price");
        b2.setBorder(new EmptyBorder(0, 0, 0, 0));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(32, 178, 170));
        b2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b2.addActionListener(this);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setBounds(350, 485, 160, 40);
        b2.setFocusable(false);
        add(b2);

        // Retrieve package and customer details
        try {
            Conn conn = new Conn();
            // Fetch package details
            PreparedStatement ps = conn.c.prepareStatement("SELECT * FROM package WHERE place=?");
            ps.setString(1, place);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t1.setText(rs.getString("place"));
                t4.setText(rs.getString("price"));
                String daysNights = rs.getString("days_nights");
                numberOfDays = parseDays(daysNights); // Parse number of days from days_nights
            }

            // Fetch customer details
            PreparedStatement ps1 = conn.c.prepareStatement("SELECT * FROM customer WHERE username=?");
            ps1.setString(1, user);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                t2.setText(rs1.getString("name"));
                t3.setText(rs1.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add listener to automatically set end date based on start date and number of days
        startDateChooser.addPropertyChangeListener("date", evt -> {
            Date startDate = startDateChooser.getDate();
            if (startDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DAY_OF_MONTH, numberOfDays); // Add the number of days
                Date endDate = cal.getTime();
                endDateChooser.setDate(endDate);
            }
        });

        setVisible(true);
    }

    // Method to parse number of days from days_nights string
    private int parseDays(String daysNights) {
        // Example input: "5 Days 4 Nights"
        int days = 0;
        try {
            String[] parts = daysNights.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equalsIgnoreCase("Days") || parts[i].equalsIgnoreCase("Day")) {
                    days = Integer.parseInt(parts[i - 1]);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Default to 1 day if parsing fails
            days = 1;
        }
        return days;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // Book Button
            try {
                int person = (Integer) spinnerPersons.getValue();
                double cost = Double.parseDouble(t4.getText());
                double totalCost = cost * person;

                // Ensure dates are selected
                Date startDate = startDateChooser.getDate();
                Date endDate = endDateChooser.getDate();

                if (startDate == null || endDate == null) {
                    JOptionPane.showMessageDialog(this, "Please select both start and end dates.");
                    return;
                }

                if (totalCost > 0) {
                    Conn conn = new Conn();
                    String sql = "INSERT INTO bookpackage (place, name, username, persons, date, end_date, totalprice) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = conn.c.prepareStatement(sql);
                    ps.setString(1, t1.getText());
                    ps.setString(2, t2.getText());
                    ps.setString(3, t3.getText());
                    ps.setString(4, String.valueOf(person));
                    String startDateStr = ((JTextField) startDateChooser.getDateEditor().getUiComponent()).getText();
                    String endDateStr = ((JTextField) endDateChooser.getDateEditor().getUiComponent()).getText();
                    ps.setString(5, startDateStr);
                    ps.setString(6, endDateStr);
                    ps.setString(7, String.format("%.2f", totalCost));
                    ps.executeUpdate();

                    // Generate PDF receipt
                    PDFGenerator.generatePackageBookingReceipt(
                        t2.getText(), // username
                        t1.getText(), // package/place name
                        String.valueOf(person), // persons
                        startDateStr, // start date
                        endDateStr, // end date
                        String.format("%.2f", totalCost) // total cost
                    );

                    JOptionPane.showMessageDialog(null, "Package Booked Successfully\nReceipt has been generated in the receipts folder");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Enter a valid number of persons.");
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Invalid input for number of persons.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == b2) { // Check Price Button
            try {
                int person = (Integer) spinnerPersons.getValue();
                double cost = Double.parseDouble(t4.getText());
                double totalCost = cost * person;

                if (totalCost > 0) {
                    l9.setText(String.format("%.2f", totalCost));
                } else {
                    l9.setText("Enter valid number of persons.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
