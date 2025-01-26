package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame implements Runnable, ActionListener {

    public Login l;
    public AdminLogin a;

    ImageIcon i1 = null, i2 = null, i3 = null, i4 = null, i5 = null, i6 = null;
    ImageIcon[] image = new ImageIcon[]{i1, i2, i3, i4, i5, i6};

    Image j1 = null, j2 = null, j3 = null, j4 = null, j5 = null, j6 = null;
    Image[] jimage = new Image[]{j1, j2, j3, j4, j5, j6};

    ImageIcon i11 = null, i12 = null, i13 = null, i14 = null, i15 = null, i16 = null;
    ImageIcon[] iimage = new ImageIcon[]{i11, i12, i13, i14, i15, i16};

    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JLabel[] jlabel = new JLabel[]{l1, l2, l3, l6, l7, l8};

    JButton b1, b2, b3, btn;
    JPanel contentPane, button, panel1;
    Thread t1;

    public static void main(String[] args) {
        new Home().setVisible(true);
    }

    Home() {
        super("Travel and Tourism Management System");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(0, 139, 139);
                Color color2 = new Color(72, 209, 204);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        button = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(0, 139, 139, 220);
                Color color2 = new Color(72, 209, 204, 220);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        button.setBounds(0, 0, 1540, 60);
        button.setLayout(null);
        contentPane.add(button);

        panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(255, 255, 255);
                Color color2 = new Color(240, 248, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        panel1.setBounds(0, 60, 1540, 750);
        panel1.setLayout(null);
        contentPane.add(panel1);

        i4 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/dd.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        i6 = new ImageIcon(i5);
        l4 = new JLabel(i6);
        l4.setBounds(5, 5, 50, 50);
        button.add(l4);

        JLabel l5 = new JLabel("DREAM DESTINATION");
        l5.setForeground(new Color(255, 255, 255));
        l5.setFont(new Font("Times New Roman", Font.BOLD, 43));
        l5.setBounds(57, 2, 800, 57);
        button.add(l5);

        b1 = new JButton("Home");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(0, 139, 139));
        b1.setBounds(1217, 7, 100, 46);
        b1.addActionListener(this);
        b1.setFocusable(false);
        button.add(b1);
        btn = b1;
        styleButton(b1);

        b2 = new JButton("User Login");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(0, 139, 139));
        b2.setBounds(1317, 7, 100, 46);
        b2.addActionListener(this);
        b2.setFocusable(false);
        button.add(b2);
        styleButton(b2);

        b3 = new JButton("Admin");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(0, 139, 139));
        b3.setBounds(1417, 7, 100, 46);
        b3.addActionListener(this);
        b3.setFocusable(false);
        button.add(b3);
        styleButton(b3);

        this.setVisible(true);
        panel1.setVisible(true);
        for (int i = 0; i <= 5; i++) {
            this.image[i] = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/homepage" + (i + 1) + ".jpg"));
            this.jimage[i] = this.image[i].getImage().getScaledInstance(1540, 750, Image.SCALE_DEFAULT);
            this.iimage[i] = new ImageIcon(this.jimage[i]);
            this.jlabel[i] = new JLabel(this.iimage[i]);
            this.jlabel[i].setBounds(0, 0, 1540, 750);
            panel1.add(this.jlabel[i]);

            JLabel l6 = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2d.setFont(new Font("Bradley Hand ITC", Font.BOLD, 70));
                    g2d.setColor(new Color(255, 255, 0, 128)); // Semi-transparent yellow
                    FontMetrics fm = g2d.getFontMetrics();
                    int textWidth = fm.stringWidth("WELCOME TO OUR TOUR WORLD");
                    g2d.drawString("WELCOME TO OUR TOUR WORLD", (getWidth() - textWidth) / 2, 100);
                }
            };
            l6.setBounds(0, 0, 1540, 750);
            this.jlabel[i].add(l6);
        }
        t1 = new Thread(this);
        t1.start();
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 128, 128));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 139, 139));
            }
        });
    }

    public void run() {
        try {
            while (true) {
                for (int i = 0; i <= 5; i++) {
                    this.jlabel[i].setVisible(true);
                    Thread.sleep(2000);
                    this.jlabel[i].setVisible(false);
                }
            }
        } catch (Exception e) {}
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            activeButton(b1);
            panel1.setVisible(true);
        }
        if (ae.getSource() == b2) {
            activeButton(b2);
            l = new Login(this);
            l.setFocusable(true);
            l.setLocation(0, 60);
            l.setVisible(true);
            contentPane.add(l);
        }
        if (ae.getSource() == b3) {
            activeButton(b3);
            a = new AdminLogin(this);
            a.setFocusable(true);
            a.setLocation(0, 60);
            a.setVisible(true);
            contentPane.add(a);
        }
    }
    
    public void activeButton(JButton b) {
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 139, 139));
        btn = b;
        btn.setForeground(Color.CYAN);
        btn.setBackground(Color.BLACK);
        disable();
    }
    
    public void disable() {
        if (panel1 != null && panel1.isVisible()) {
            panel1.setVisible(false);
        } else if (l != null && l.isVisible()) {
            l.setVisible(false);
        } else if (a != null && a.isVisible()) {
            a.setVisible(false);
        }
    }
}
