package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;

public class BookHotel extends JFrame implements ActionListener {

    JPanel headerpanel;
    JTextField t1, t2, t3, t4, t5, t6, t8;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13;
    Choice c1, c2;
    JButton b1, b2;
    String name, user;
    JSpinner spinner;

    public BookHotel(String name, String user) {
        this.name = name;
        this.user = user;
        setSize(750, 735);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        headerpanel = new JPanel();
        headerpanel.setBackground(new Color(32, 178, 170));
        headerpanel.setBounds(0, 0, 750, 50);
        add(headerpanel);
        headerpanel.setLayout(null);

        l1 = new JLabel("Book Hotel");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(0, 5, 750, 50);
        headerpanel.add(l1);

        l2 = new JLabel("Hotel Name: ");
        l2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l2.setBounds(60, 80, 200, 40);
        add(l2);

        l3 = new JLabel("Customer Name: ");
        l3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l3.setBounds(60, 130, 200, 40);
        add(l3);

        l4 = new JLabel("Username: ");
        l4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l4.setBounds(60, 180, 200, 40);
        add(l4);

        l5 = new JLabel("Hotel Cost AC: ");
        l5.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l5.setBounds(60, 230, 200, 40);
        add(l5);

        l6 = new JLabel("Food Cost Per Day: ");
        l6.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l6.setBounds(60, 280, 200, 40);
        add(l6);

        l7 = new JLabel("Hotel Cost Non-AC: ");
        l7.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l7.setBounds(60, 330, 200, 40);
        add(l7);

        l8 = new JLabel("Total Persons: ");
        l8.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l8.setBounds(60, 380, 200, 40);
        add(l8);

        l9 = new JLabel("No. Of Days: ");
        l9.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l9.setBounds(60, 430, 200, 40);
        add(l9);

        l10 = new JLabel("AC/Non-AC: ");
        l10.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l10.setBounds(60, 480, 200, 40);
        add(l10);

        l11 = new JLabel("Food Included: ");
        l11.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l11.setBounds(60, 530, 200, 40);
        add(l11);

        l12 = new JLabel("Total Price: ");
        l12.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l12.setBounds(60, 580, 200, 40);
        add(l12);

        t1 = new JTextField();
        t1.setEditable(false);
        t1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t1.setBounds(270, 80, 380, 40);
        add(t1);

        t2 = new JTextField();
        t2.setEditable(false);
        t2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t2.setBounds(270, 130, 380, 40);
        add(t2);

        t3 = new JTextField();
        t3.setEditable(false);
        t3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t3.setBounds(270, 180, 380, 40);
        add(t3);

        t4 = new JTextField();
        t4.setEditable(false);
        t4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t4.setBounds(270, 230, 380, 40);
        add(t4);

        t5 = new JTextField();
        t5.setEditable(false);
        t5.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t5.setBounds(270, 280, 380, 40);
        add(t5);

        t6 = new JTextField();
        t6.setEditable(false);
        t6.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t6.setBounds(270, 330, 380, 40);
        add(t6);

        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 100, 1); // Initial value: 1, Min: 1, Max: 100, Step: 1
        spinner = new JSpinner(model);
        spinner.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        spinner.setBounds(270, 380, 380, 40);

        // Customize the editor to align text to the left
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        JTextField textField = editor.getTextField();
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));

        add(spinner);

        t8 = new JTextField();
        t8.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t8.setBounds(270, 430, 380, 40);
        add(t8);

        c1 = new Choice();
        c1.add("AC");
        c1.add("Non-AC");
        c1.setBounds(270, 482, 380, 40);
        add(c1);

        c2 = new Choice();
        c2.add("Yes");
        c2.add("No");
        c2.setBounds(270, 532, 380, 40);
        add(c2);

        l13 = new JLabel();
        l13.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        l13.setBounds(270, 580, 380, 40);
        l13.setForeground(Color.BLUE);
        add(l13);

        try {
            Conn conn = new Conn();
            PreparedStatement ps = conn.c.prepareStatement("select * from hotel where name='" + name + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t1.setText(rs.getString("name"));
                t4.setText(rs.getString("ac"));
                t5.setText(rs.getString("food"));
                t6.setText(rs.getString("hotelcost"));
            }
            PreparedStatement ps1 = conn.c.prepareStatement("select * from customer where username='" + user + "'");
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                t2.setText(rs1.getString("name"));
                t3.setText(rs1.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        b1 = new JButton("Book");
        b1.setBounds(578, 645, 140, 38);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Check Price");
        b2.setBounds(427, 645, 140, 38);
        b2.addActionListener(this);
        add(b2);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                Conn conn = new Conn();
                String sql = "insert into bookhotel values(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(sql);
                ps.setString(1, t1.getText());
                ps.setString(2, t2.getText());
                ps.setString(3, t3.getText());
                ps.setString(4, String.valueOf(spinner.getValue()));
                ps.setString(5, t8.getText());
                ps.setString(6, c1.getSelectedItem());
                ps.setString(7, c2.getSelectedItem());
                ps.setString(8, l13.getText());
                ps.executeUpdate();
                
                // Generate PDF receipt
                PDFGenerator.generateHotelBookingReceipt(
                    t3.getText(), // username
                    t1.getText(), // hotel name
                    String.valueOf(spinner.getValue()), // persons
                    t8.getText(), // days
                    c1.getSelectedItem(), // ac
                    c2.getSelectedItem(), // food
                    l13.getText() // total cost
                );
                
                JOptionPane.showMessageDialog(null, "Hotel Booked Successfully\nReceipt has been generated in the receipts folder");
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ae.getSource() == b2) {
            try {
                double accost = Double.parseDouble(t4.getText());
                double foodcost = Double.parseDouble(t5.getText());
                double hotelcost = Double.parseDouble(t6.getText());
                int person = (int) spinner.getValue();
                double days = Double.parseDouble(t8.getText());
                double ac = accost * days;
                double food = foodcost * days;
                double hotel = hotelcost * days * person;
                DecimalFormat df = new DecimalFormat("####0.00");
                if (c1.getSelectedItem().equalsIgnoreCase("Non-AC")) {
                    ac = 0;
                }
                if (c2.getSelectedItem().equalsIgnoreCase("No")) {
                    food = 0;
                }
                l13.setText(df.format(ac + food + hotel));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
