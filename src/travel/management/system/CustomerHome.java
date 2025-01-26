package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerHome extends JFrame implements ActionListener {

    public JPanel contentPane;
    public JPanel profilepanel;

    private final Color buttonbcolor = new Color(70, 130, 180); // Steel Blue
    private final Color buttonbcolorHover = new Color(100, 149, 237); // Cornflower Blue
    private final Color buttonfcolor = Color.WHITE;
    private final Font buttonfont = new Font("SansSerif", Font.PLAIN, 18);

    private final Color sidebarColor = new Color(52, 73, 94); // Dark Blue-Gray
    private final Color profileBgColor = new Color(30, 30, 80); // Slightly Darker Blue
    private final Color buttonActiveFontColor = new Color(140, 230, 167); // #56C1FE

    JButton b1, b2, b3, b4, b5, b6, b7, btn;
    JLabel l1;
    public int panely = 0, panelx = 250, row = 0;

    public HomePanel homepanel;
    public ProfilePanel yourprofile;
    public PackagePanel packagepanel;
    public HotelPanel hotelpanel;
    public BookedPackagePanel bookedpackagepanel;
    public BookedHotelPanel bookedhotelpanel;

    String user, name = "Guest";

    public static void main(String[] args) {
        new CustomerHome("").setVisible(true);
    }

    CustomerHome(String user) {

        super("CustomerHome");
        this.user = user;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Conn conn = new Conn();
            PreparedStatement ps = conn.c.prepareStatement("select * from account where username='" + user + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        profilepanel = new JPanel();
        profilepanel.setBounds(5, 7, 240, 63);
        profilepanel.setBackground(profileBgColor);
        profilepanel.setLayout(null);
        contentPane.add(profilepanel);

        l1 = new JLabel(name);
        l1.setForeground(Color.WHITE);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setOpaque(true);
        l1.setBackground(profileBgColor);
        l1.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
        adjustLabelFont(l1, profilepanel.getWidth());
        l1.setBounds(0, 14, 240, 36);
        profilepanel.add(l1);

        createHomepanel();

        JPanel sidebarpanel = new JPanel();
        sidebarpanel.setBackground(sidebarColor); // Updated sidebar color
        sidebarpanel.setBounds(5, 75, 240, 714);
        contentPane.add(sidebarpanel);
        sidebarpanel.setLayout(null);

        b1 = createButton("Home");
        sidebarpanel.add(b1);
        btn = b1;

        b2 = createButton("Your Profile");
        sidebarpanel.add(b2);

        b3 = createButton("Travel Packages");
        sidebarpanel.add(b3);

        b4 = createButton("Hotels");
        sidebarpanel.add(b4);

        b5 = createButton("Booked Packages");
        sidebarpanel.add(b5);

        b6 = createButton("Booked hotels");
        sidebarpanel.add(b6);

        b7 = createButton("LogOut");
        sidebarpanel.add(b7);

        activeButton(b1);
        homepanel.setVisible(true);
    }

    public void createHomepanel() {
        homepanel = new HomePanel(this, user);
        homepanel.setLocation(panelx, panely);
        homepanel.setFocusable(true);
        contentPane.add(homepanel);
    }

    public void activeButton(JButton button) {
        btn.setBackground(buttonbcolor);
        btn.setForeground(buttonfcolor);
        btn.setFont(buttonfont);
        btn.setBorder(new LineBorder(buttonfcolor, 1)); // Reset the previous button's border
        btn = button;
        btn.setForeground(buttonActiveFontColor); // Set active button font color
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setBorder(new LineBorder(new Color(41, 128, 185), 2)); // Add a thicker border for the active button
        disablePanel();
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(buttonfcolor);
        button.setFont(new Font(buttonfont.getFontName(), buttonfont.getStyle(), buttonfont.getSize() + 2)); // Increase font size
        button.setBackground(buttonbcolor);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new LineBorder(buttonfcolor, 1)); // Add light gray border
        button.addActionListener(this);

        button.setLocation(0, row);
        button.setSize(234, 50); // Increased height of the button
        row += 50; // Increase the space between buttons

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(buttonbcolorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != btn) { // Reset background and border only if not the active button
                    button.setBackground(buttonbcolor);
                    button.setBorder(new LineBorder(buttonfcolor, 1));
                }
            }
        });

        return button;
    }

    public void disablePanel() {
        if (homepanel != null && homepanel.isVisible()) {
            homepanel.setVisible(false);
        } else if (packagepanel != null && packagepanel.isVisible()) {
            packagepanel.setVisible(false);
        } else if (yourprofile != null && yourprofile.isVisible()) {
            yourprofile.setVisible(false);
        } else if (hotelpanel != null && hotelpanel.isVisible()) {
            hotelpanel.setVisible(false);
        } else if (bookedpackagepanel != null && bookedpackagepanel.isVisible()) {
            bookedpackagepanel.setVisible(false);
        } else if (bookedhotelpanel != null && bookedhotelpanel.isVisible()) {
            bookedhotelpanel.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openPanel(e.getSource());
    }

    public void openPanel(Object source) {
        if (source == b1) {
            activeButton(b1);
            homepanel = new HomePanel(this, user);
            homepanel.setLocation(panelx, panely);
            homepanel.setFocusable(true);
            contentPane.add(homepanel);

        } else if (source == b2) {
            activeButton(b2);
            yourprofile = new ProfilePanel(this, user);
            yourprofile.setLocation(panelx, panely);
            yourprofile.setFocusable(true);
            contentPane.add(yourprofile);

        } else if (source == b3) {
            activeButton(b3);
            packagepanel = new PackagePanel(this, user);
            packagepanel.setLocation(panelx, panely);
            packagepanel.setFocusable(true);
            contentPane.add(packagepanel);

        } else if (source == b4) {
            activeButton(b4);
            hotelpanel = new HotelPanel(this, user);
            hotelpanel.setLocation(panelx, panely);
            hotelpanel.setFocusable(true);
            contentPane.add(hotelpanel);

        } else if (source == b5) {
            activeButton(b5);
            bookedpackagepanel = new BookedPackagePanel(user);
            bookedpackagepanel.setLocation(panelx, panely);
            bookedpackagepanel.setFocusable(true);
            contentPane.add(bookedpackagepanel);

        } else if (source == b6) {
            activeButton(b6);
            bookedhotelpanel = new BookedHotelPanel(user);
            bookedhotelpanel.setLocation(panelx, panely);
            bookedhotelpanel.setFocusable(true);
            contentPane.add(bookedhotelpanel);

        } else if (source == b7) {
            activeButton(b7);
            this.dispose();
            new Home().setVisible(true);
        }
    }

    private void adjustLabelFont(JLabel label, int maxWidth) {
        Font labelFont = label.getFont();
        String text = label.getText();
        int fontSize = labelFont.getSize();

        FontMetrics metrics = label.getFontMetrics(labelFont);
        while (metrics.stringWidth(text) > maxWidth && fontSize > 10) {
            fontSize--;
            label.setFont(new Font(labelFont.getName(), labelFont.getStyle(), fontSize));
            metrics = label.getFontMetrics(label.getFont());
        }
    }
}
