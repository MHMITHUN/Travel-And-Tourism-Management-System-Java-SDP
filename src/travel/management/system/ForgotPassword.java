package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class ForgotPassword extends JPanel implements ActionListener {

    JPanel p1;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2, b3;
    JLabel l6;
    Image backgroundImage;

    public static void main(String[] args) {
        new ForgotPassword().setVisible(true);
    }

    ForgotPassword() {
        // Load the background image
        backgroundImage = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/logins.jpg")).getImage();

        setSize(1518, 800);
        setBackground(Color.WHITE);
        setLayout(null);

        // Header Label
        l6 = new JLabel("FORGOT PASSWORD");
        l6.setBounds(500, 100, 500, 50);
        l6.setFont(new Font("Tahoma", Font.BOLD, 36));
        l6.setForeground(new Color(54, 33, 89)); // Sleek dark purple color
        l6.setHorizontalAlignment(SwingConstants.CENTER);
        add(l6);

        // Panel for Form
        p1 = new JPanel();
        p1.setBounds(500, 200, 500, 350); // Adjusted position and size
        p1.setBackground(new Color(245, 245, 245, 100)); // Subtle gray background with transparency
        p1.setBorder(BorderFactory.createLineBorder(new Color(54, 33, 89), 2)); // Border for better look
        p1.setLayout(null);
        add(p1);

        // Labels
        JLabel l1 = new JLabel("Username:");
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(30, 20, 165, 30);
        p1.add(l1);

        JLabel l2 = new JLabel("Name:");
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(30, 60, 165, 30);
        p1.add(l2);

        JLabel l3 = new JLabel("Security Question:");
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(30, 100, 165, 30);
        p1.add(l3);

        JLabel l4 = new JLabel("Answer:");
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(30, 140, 165, 30);
        p1.add(l4);

        JLabel l5 = new JLabel("Password:");
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(30, 180, 165, 30);
        p1.add(l5);

        // Text Fields with transparent blur effect
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
        t1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t1.setBounds(205, 25, 180, 25);
        t1.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
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
        t2.setEditable(false);
        t2.setBackground(new Color(255, 255, 255));
        t2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t2.setBounds(205, 65, 180, 25);
        t2.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
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
        t3.setEditable(false);
        t3.setBackground(new Color(255, 255, 255));
        t3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t3.setBounds(205, 105, 180, 25);
        t3.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
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
        t4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t4.setBounds(205, 145, 180, 25);
        t4.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
        p1.add(t4);

        t5 = new JTextField() {
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
        t5.setEditable(false);
        t5.setBackground(new Color(255, 255, 255));
        t5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t5.setBounds(205, 185, 180, 25);
        t5.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
        p1.add(t5);

        // Buttons
        b1 = new JButton("Search");
        b1.addActionListener(this);
        b1.setFont(new Font("Tahoma", Font.BOLD, 12));
        b1.setBounds(390, 25, 90, 25); // Added proper space
        b1.setBackground(new Color(54, 33, 89));
        b1.setForeground(Color.WHITE);
        p1.add(b1);

        b2 = new JButton("Retrieve");
        b2.addActionListener(this);
        b2.setFont(new Font("Tahoma", Font.BOLD, 12));
        b2.setBounds(390, 145, 90, 25); // Added proper space
        b2.setBackground(new Color(54, 33, 89));
        b2.setForeground(Color.WHITE);
        p1.add(b2);

        b3 = new JButton("Back");
        b3.addActionListener(this);
        b3.setFont(new Font("Tahoma", Font.BOLD, 13));
        b3.setBounds(200, 250, 100, 25);
        b3.setBackground(new Color(54, 33, 89));
        b3.setForeground(Color.WHITE);
        p1.add(b3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

        public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn();
            if (ae.getSource() == b1) {
                String sql = "select * from account where username=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    t2.setText(rs.getString("name"));
                    t3.setText(rs.getString("security"));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username!!!");
                }
            } else if (ae.getSource() == b2) {
                String sql = "select * from account where username=? and answer=?";
                PreparedStatement st = con.c.prepareStatement(sql);
                st.setString(1, t1.getText());
                st.setString(2, t4.getText());
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    t5.setText(rs.getString("password"));
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Answer!!!");
                }
            } else if (ae.getSource() == b3) {
                Login n = new Login();
                l6.setVisible(false);
                p1.setVisible(false);
                n.setFocusable(true);
                add(n);
                n.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
