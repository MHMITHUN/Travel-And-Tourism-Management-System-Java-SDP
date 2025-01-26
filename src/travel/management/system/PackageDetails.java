package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class PackageDetails extends JPanel implements ActionListener {
    
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JButton b1, b2, b3; // "Delete Package" button
    JPanel panel, panel1;
    JTextArea ta1;
    public AdminHome a;
    public CustomerHome c;
    String place, user;

    // Constructor for Admin Panel
    PackageDetails(AdminHome a, String place) {
        this();
        this.a = a;
        this.place = place;
        display();
        b3.setVisible(true); // Show "Delete Package" button
    }

    // Constructor for Customer Panel
    PackageDetails(CustomerHome c, String place, String user) {
        this();
        this.c = c;
        this.place = place;
        this.user = user;
        b1.setText("Book Package");
        display();
        b3.setVisible(false); // Hide "Delete Package" button
    }

    // Common Constructor
    PackageDetails() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        this.setSize(1278, 900);
        setLayout(null); 
        
        panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(7, 7, 1278, 160);
        add(panel);
        panel.setLayout(null);
                
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(7, 167, 1278, 740);
        add(panel1);
        panel1.setLayout(null);
        
        l11 = new JLabel("Package Details ");
        l11.setBounds(5, 55, 300, 45);
        panel.add(l11);
        l11.setBackground(new Color(32, 178, 170));
        l11.setForeground(Color.WHITE);
        l11.setFont(new Font("Segoe UI", Font.BOLD, 30));
                
        l1 = new JLabel("Place");
        l1.setBounds(2, 400, 274, 45);
        panel1.add(l1);
        
        l2 = new JLabel("No. Of Days and Nights");
        l2.setBounds(2, 444, 274, 45);
        panel1.add(l2);
        
        l3 = new JLabel();
        l3.setBounds(275, 400, 360, 45);
        panel1.add(l3);
        
        l4 = new JLabel();
        l4.setBounds(275, 444, 360, 45);
        panel1.add(l4);
                
        l5 = new JLabel("City/State");
        l5.setBounds(634, 400, 274, 45);
        panel1.add(l5);
        
        l6 = new JLabel("Price");
        l6.setBounds(634, 444, 274, 45);
        panel1.add(l6);
        
        l7 = new JLabel();
        l7.setBounds(907, 400, 360, 45);
        panel1.add(l7);
        
        l8 = new JLabel();
        l8.setBounds(907, 444, 360, 45);
        panel1.add(l8);
        
        l9 = new JLabel("Description");
        l9.setBounds(55, 488, 274, 105);
        panel1.add(l9);
        
        ta1 = new JTextArea();
        ta1.setBounds(328, 488, 885, 105);
        ta1.setLineWrap(true);
        panel1.add(ta1);
        
        l10 = new JLabel();
        l10.setBounds(385, 20, 500, 360);
        panel1.add(l10);
                
        b1 = new JButton("Edit Package");
        b1.setBounds(1110, 120, 140, 30);
        panel.add(b1);
        b1.addActionListener(this);
        
        b2 = new JButton("Back");
        b2.setBounds(20, 120, 140, 30);
        panel.add(b2);
        b2.addActionListener(this);

        // "Delete Package" Button
        b3 = new JButton("Delete Package");
        b3.setBounds(950, 120, 140, 30);
        panel.add(b3);
        b3.addActionListener(this);
        b3.setVisible(false); // Default to hidden
    }

    public void display() {
        try {
            Conn conn = new Conn();
            PreparedStatement ps = conn.c.prepareStatement("SELECT * FROM package WHERE place = ?");
            ps.setString(1, place);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                l3.setText(rs.getString(1)); 
                l4.setText(rs.getString(4)); 
                l7.setText(rs.getString(2));
                l8.setText(rs.getString(3));
                ta1.setText(rs.getString(5)); 
                byte[] photo = rs.getBytes(6);
                Image i1 = new ImageIcon(photo).getImage().getScaledInstance(500, 360, Image.SCALE_SMOOTH);
                l10.setIcon(new ImageIcon(i1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { 
            if (a != null) {
                new AddPackage(this, place).setVisible(true);
            }
            if (c != null) {
                new BookPackage(place, user).setVisible(true);
            }
        } else if (ae.getSource() == b2) { 
            panel.setVisible(false);
            panel1.setVisible(false);
            if (a != null) {
                PackagePanel pp = new PackagePanel(a);
                add(pp);
                pp.setVisible(true);
            }
            if (c != null) {
                PackagePanel pp = new PackagePanel(c, user);
                add(pp);
                pp.setVisible(true);
            }
        } else if (ae.getSource() == b3) { 
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this package?", 
                "Delete Confirmation", 
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn conn = new Conn();
                    PreparedStatement ps = conn.c.prepareStatement("DELETE FROM package WHERE place = ?");
                    ps.setString(1, place);
                    int rowsAffected = ps.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Package deleted successfully!");
                        panel.setVisible(false);
                        panel1.setVisible(false);
                        if (a != null) {
                            PackagePanel pp = new PackagePanel(a);
                            add(pp);
                            pp.setVisible(true);
                        }
                        if (c != null) {
                            PackagePanel pp = new PackagePanel(c, user);
                            add(pp);
                            pp.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete the package.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                }
            }
        }
    }
}
