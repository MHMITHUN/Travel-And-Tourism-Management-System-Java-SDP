package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JPanel implements ActionListener {

    JPanel p1;
    JTextField t1;
    JPasswordField passwordField;
    JButton b1;
    JCheckBox showPasswordCheckBox;
    Home home;

    AdminLogin(Home m) {
        this.home = m;
        setSize(1518, 800);
        setLayout(null);

        // Background Panel
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/adpic.jpg")).getImage().getScaledInstance(1518, 800, Image.SCALE_SMOOTH))); 
        background.setBounds(0, 0, 1518, 800);
        add(background);

        p1 = new JPanel();
        p1.setBackground(new Color(51, 51, 51, 200)); // Slight transparency for modern look
        p1.setBounds(500, 150, 500, 500); // Adjusted size and position
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0), 2)); // Orange border for contrast
        background.add(p1);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(30, 50, 100, 25);
        l1.setFont(new Font("Arial", Font.BOLD, 18));
        l1.setForeground(Color.WHITE);
        p1.add(l1);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(30, 140, 100, 25);
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l2.setForeground(Color.WHITE);
        p1.add(l2);

        t1 = new JTextField();
        t1.setBounds(30, 85, 440, 30); // Adjusted width
        t1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        t1.setFont(new Font("Arial", Font.PLAIN, 16));
        p1.add(t1);

        passwordField = new JPasswordField();
        passwordField.setBounds(30, 175, 440, 30); // Adjusted width
        passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        p1.add(passwordField);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(30, 210, 150, 25);
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        showPasswordCheckBox.setBackground(new Color(51, 51, 51, 200));
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.addActionListener(this);
        p1.add(showPasswordCheckBox);

        b1 = new JButton("Login");
        b1.addActionListener(this);
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(255, 69, 0));
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBounds(30, 250, 440, 40); // Adjusted width
        p1.add(b1);

        JLabel l3 = new JLabel("<html>Welcome to the Admin Panel. Please login using your credentials.</html>");
        l3.setBounds(30, 300, 440, 60); // Adjusted width and used HTML for text wrapping
        l3.setFont(new Font("Arial", Font.PLAIN, 14));
        l3.setForeground(Color.WHITE);
        p1.add(l3);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                Conn con = new Conn();
                String sql = "select * from adminlogin where username=? And password=?";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText());
                st.setString(2, String.valueOf(passwordField.getPassword()));

                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    new AdminHome(t1.getText()).setVisible(true);
                    home.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username Or Password!!!");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        if (ae.getSource() == showPasswordCheckBox) {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);  // Show password
            } else {
                passwordField.setEchoChar('*');  // Hide password
            }
        }
    }
}
