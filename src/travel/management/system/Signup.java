package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Signup extends JPanel implements ActionListener {

    JPanel p1;
    JTextField t1, t2, t3, t4;
    JButton b1, b2;
    JComboBox<String> c1;
    JLabel l5;
    Image backgroundImage;

    public Signup() {
        // Load the background image
        backgroundImage = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/logins.jpg")).getImage();

        setSize(1518, 800);
        setBackground(new Color(240, 248, 255));
        setLayout(null);

        p1 = new JPanel();
        p1.setBounds(500, 200, 500, 380); // Adjusted position and size
        p1.setBackground(new Color(180, 200, 255, 100)); // Subtle gray background with transparency
        p1.setBorder(BorderFactory.createLineBorder(new Color(100, 120, 255), 3));
        p1.setLayout(null);
        add(p1);

        JLabel l = new JLabel("Username:");
        l.setForeground(new Color(70, 70, 70));
        l.setFont(new Font("Tahoma", Font.BOLD, 14));
        l.setBounds(30, 40, 140, 30);
        p1.add(l);

        JLabel ll = new JLabel("Name:");
        ll.setForeground(new Color(70, 70, 70));
        ll.setFont(new Font("Tahoma", Font.BOLD, 14));
        ll.setBounds(30, 90, 140, 30);
        p1.add(ll);

        JLabel l2 = new JLabel("Password:");
        l2.setForeground(new Color(70, 70, 70));
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(30, 140, 140, 30);
        p1.add(l2);

        JLabel l3 = new JLabel("Security Question:");
        l3.setForeground(new Color(70, 70, 70));
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(30, 190, 140, 30);
        p1.add(l3);

        JLabel l4 = new JLabel("Answer:");
        l4.setForeground(new Color(70, 70, 70));
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(30, 240, 140, 30);
        p1.add(l4);

        l5 = new JLabel("CREATE ACCOUNT");
        l5.setBounds(510, 100, 500, 50);
        l5.setFont(new Font("Segoe UI", Font.BOLD, 40));
        l5.setForeground(new Color(100, 149, 237));
        add(l5);

        c1 = new JComboBox<>(new String[]{
            "Your Nickname?", "Your Lucky Number?",
            "Your Favorite Anime?", "Your Childhood Name?"
        });
        c1.setBounds(200, 195, 250, 25);
        c1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        c1.setBackground(Color.WHITE);
        p1.add(c1);

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
        t1.setBounds(200, 45, 250, 25);
        t1.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
        t1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        p1.add(t1);

        t2 = new JTextField() {
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
        t2.setBackground(new Color(255, 255, 255));
        t2.setBounds(200, 95, 250, 25);
        t2.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
        t2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        p1.add(t2);

        t3 = new JTextField() {
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
        t3.setBackground(new Color(255, 255, 255));
        t3.setBounds(200, 145, 250, 25);
        t3.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
        t3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        p1.add(t3);

        t4 = new JTextField() {
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
        t4.setBackground(new Color(255, 255, 255));
        t4.setBounds(200, 245, 250, 25);
        t4.setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211)));
        t4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        p1.add(t4);

        b1 = new JButton("Create");
        b1.setBounds(80, 300, 150, 35);
        b1.setFont(new Font("Tahoma", Font.BOLD, 14));
        b1.setBackground(new Color(100, 149, 237));
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.addActionListener(this);
        p1.add(b1);

        b2 = new JButton("Login");
        b2.setBounds(270, 300, 150, 35);
        b2.setFont(new Font("Tahoma", Font.BOLD, 14));
        b2.setBackground(new Color(60, 179, 113));
        b2.setForeground(Color.WHITE);
        b2.setFocusPainted(false);
        b2.addActionListener(this);
        p1.add(b2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn();

            if (ae.getSource() == b1) {
                String username = t1.getText().trim();
                String name = t2.getText();
                String password = t3.getText();
                String securityQuestion = (String) c1.getSelectedItem();
                String answer = t4.getText();

                // Validate the password
                if (!PasswordChecker.isIdealPassword(password)) {
                    String feedback = PasswordChecker.getValidationFeedback(password);
                    JOptionPane.showMessageDialog(null, feedback, "Password Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO account(username, name, password, security, answer) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, username);
                st.setString(2, name);
                st.setString(3, password);
                st.setString(4, securityQuestion);
                st.setString(5, answer);

                               String pr = "SELECT * FROM account WHERE username=?";
                PreparedStatement st1 = con.c.prepareStatement(pr);
                st1.setString(1, username);

                ResultSet rs = st1.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username Already Exists!!!");
                } else {
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");

                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                }
            } else if (ae.getSource() == b2) {
                Login n = new Login();
                l5.setVisible(false);
                p1.setVisible(false);
                n.setFocusable(true);
                this.add(n);
                n.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class PasswordChecker {
        public static boolean isIdealPassword(String password) {
            boolean hasMinLength = password.length() >= 8;
            boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
            boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
            boolean hasDigit = password.chars().anyMatch(Character::isDigit);
            boolean hasSpecial = password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
            return hasMinLength && hasUpper && hasLower && hasDigit && hasSpecial;
        }

        public static String getValidationFeedback(String password) {
            StringBuilder feedback = new StringBuilder("Password must:\n");
            feedback.append(String.format(" - %s\n", password.length() >= 8 ? "✔ Be at least 8 characters long" : "✘ Be at least 8 characters long"));
            feedback.append(String.format(" - %s\n", password.chars().anyMatch(Character::isUpperCase) ? "✔ Include at least one uppercase letter" : "✘ Include at least one uppercase letter"));
            feedback.append(String.format(" - %s\n", password.chars().anyMatch(Character::isLowerCase) ? "✔ Include at least one lowercase letter" : "✘ Include at least one lowercase letter"));
            feedback.append(String.format(" - %s\n", password.chars().anyMatch(Character::isDigit) ? "✔ Include at least one digit" : "✘ Include at least one digit"));
            feedback.append(String.format(" - %s\n", password.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch)) ? "✔ Include at least one special character" : "✘ Include at least one special character"));
            return feedback.toString();
        }
    }
}
