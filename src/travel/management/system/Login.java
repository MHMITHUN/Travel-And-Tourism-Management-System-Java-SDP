package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JPanel implements ActionListener {

    JPanel p1;
    JTextField t1;
    JPasswordField passwordField;
    JCheckBox showPassword;
    JButton b1, b2, b3;
    Home home;
    JLabel l5;
    JLabel background;

    Login() {

        setSize(1540, 800);
        setBackground(new Color(240, 248, 255)); // Light blue background
        setLayout(null);

        // Setting the background image
        updateBackgroundImage();

        p1 = new JPanel();
        p1.setBackground(new Color(255, 239, 213, 100)); // Peach puff with transparency
        p1.setBounds(540, 200, 460, 400); // Adjusted position and size
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createLineBorder(new Color(205, 92, 92), 2)); // Dark red border
        background.add(p1);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(90, 50, 100, 25);
        l1.setFont(new Font("SansSerif", Font.BOLD, 16));
        l1.setForeground(new Color(70, 130, 180)); // Steel blue
        p1.add(l1);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(90, 120, 100, 25);
        l2.setFont(new Font("SansSerif", Font.BOLD, 16));
        l2.setForeground(new Color(70, 130, 180)); // Steel blue
        p1.add(l2);

        t1 = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    int w = getWidth();
                    int h = getHeight();
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // Transparency level
                    g2.setColor(getBackground());
                    g2.fillRect(0, 0, w, h);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        t1.setBackground(new Color(255, 255, 255));
        t1.setBounds(90, 75, 280, 25);
        t1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        t1.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Steel blue border with thickness 2
        p1.add(t1);

        passwordField = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    int w = getWidth();
                    int h = getHeight();
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // Transparency level
                    g2.setColor(getBackground());
                    g2.fillRect(0, 0, w, h);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setBounds(90, 145, 280, 25);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2)); // Steel blue border with thickness 2
        p1.add(passwordField);

        // Show Password Checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(90, 175, 150, 25);
        showPassword.setBackground(new Color(255, 239, 213, 100)); // Peach puff with transparency
        showPassword.setFont(new Font("SansSerif", Font.ITALIC, 12));
        showPassword.setFocusPainted(false);
        showPassword.addActionListener(this);
        p1.add(showPassword);

        l5 = new JLabel("LOGIN");
        l5.setBounds(560, 130, 240, 50); // Adjusted position for better visibility
        l5.setFont(new Font("Times New Roman", Font.BOLD, 42));
        l5.setForeground(new Color(138, 43, 226)); // Blue Violet
        background.add(l5);

        b1 = new JButton("Login");
        b1.setBounds(90, 210, 120, 30);
        b1.setBackground(new Color(100, 149, 237)); // Cornflower blue
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SansSerif", Font.BOLD, 14));
        b1.setBorder(BorderFactory.createEmptyBorder());
        b1.addActionListener(this);
        p1.add(b1);

        b2 = new JButton("Sign Up");
        b2.setBounds(250, 210, 120, 30);
        b2.setBackground(new Color(60, 179, 113)); // Medium sea green
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("SansSerif", Font.BOLD, 14));
        b2.setBorder(BorderFactory.createEmptyBorder());
        b2.addActionListener(this);
        p1.add(b2);

        b3 = new JButton("Forgot Password?");
        b3.setBounds(160, 260, 160, 30);
        b3.setBackground(new Color(255, 140, 0)); // Dark orange
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("SansSerif", Font.BOLD, 12));
        b3.setBorder(BorderFactory.createEmptyBorder());
        b3.addActionListener(this);
        p1.add(b3);
    }

    public Login(Home h) {
        this();
        this.home = h;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('*'); // Hide password
            }
        } else if (ae.getSource() == b1) {
            try {
                Conn con = new Conn();
                String sql = "select * from account where username=? and password=?";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText());
                st.setString(2, passwordField.getText());

                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    this.setVisible(false);
                    new CustomerHome(t1.getText()).setVisible(true);
                    home.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!!!");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            Signup s = new Signup();
            this.removeAll();
            this.add(s);
            s.setVisible(true);
            revalidate();
            repaint();

        } else if (ae.getSource() == b3) {
            ForgotPassword f = new ForgotPassword();
            this.removeAll();
            this.add(f);
            f.setVisible(true);
            revalidate();
            repaint();
        }
    }

    private void updateBackgroundImage() {
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/logins.jpg"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(1540, 800, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        background = new JLabel(scaledIcon);
        background.setBounds(0, 0, 1540, 800);
        setLayout(null);
        add(background);
    }

    public void resetBackgroundImage() {
        removeAll();
        updateBackgroundImage();
        revalidate();
        repaint();
    }
}
