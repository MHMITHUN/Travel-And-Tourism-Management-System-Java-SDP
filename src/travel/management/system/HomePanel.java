package travel.management.system;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.net.URL;

public class HomePanel extends JPanel {
    JPanel homeheaderpanel;
    JLabel welcomelabel, packageLabel, imageOverlayLabel;
    String user;
    ArrayList<String> packages; 
    HashMap<String, ImageIcon> packageImages;
    int currentPackageIndex = 0;

    HomePanel() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        this.setSize(1335, 680);
        setLayout(null);

        homeheaderpanel = new JPanel();
        homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        homeheaderpanel.setBackground(new Color(32, 178, 170));
        homeheaderpanel.setLayout(null);
        homeheaderpanel.setBounds(0, 8, 1335, 63);
        add(homeheaderpanel);

        JLabel lblHome = new JLabel("Home Page");
        lblHome.setForeground(Color.WHITE);
        lblHome.setHorizontalAlignment(SwingConstants.LEFT);
        lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
        lblHome.setBounds(10, 8 , 380, 45);
        homeheaderpanel.add(lblHome);

        welcomelabel = new JLabel("Welcome");
        welcomelabel.setHorizontalAlignment(SwingConstants.LEFT);
        welcomelabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        welcomelabel.setForeground(new Color(255, 204, 51));
        welcomelabel.setBounds(10, 75, 600, 50);
        add(welcomelabel);

        JLabel backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 125, 1335, getHeight() - 125);
        backgroundImage.setLayout(null);
        add(backgroundImage);

        imageOverlayLabel = new JLabel();
        imageOverlayLabel.setOpaque(true);
        imageOverlayLabel.setBackground(new Color(0, 0, 0, 100));
        imageOverlayLabel.setForeground(Color.WHITE);
        imageOverlayLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        imageOverlayLabel.setBounds(10, 10, 300, 40);
        backgroundImage.add(imageOverlayLabel);

        packages = new ArrayList<>();
        packageImages = new HashMap<>();

        
        URL loadImageURL = ClassLoader.getSystemResource("Travel/Management/System/icons/looa.jpg");
        if (loadImageURL != null) {
            backgroundImage.setIcon(new ImageIcon(loadImageURL));
        } else {
            System.err.println("Placeholder image not found!");
        }
        imageOverlayLabel.setText("Loading packages...");

        loadPackagesAndImagesAsync(backgroundImage);
    }

    public HomePanel(AdminHome a) {
        this();
    }

    public HomePanel(CustomerHome c, String user) {
        this();
        this.user = user;
        try {
            Conn conn = new Conn();
            PreparedStatement ps = conn.c.prepareStatement("SELECT * FROM account WHERE username = ?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("name");
                welcomelabel.setText("Welcome, " + fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPackagesAndImagesAsync(JLabel backgroundImage) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Conn conn = new Conn();
                    PreparedStatement ps = conn.c.prepareStatement("SELECT place, image FROM package");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String packageName = rs.getString("place");

                        
                        Blob blob = rs.getBlob("image");
                        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                        ImageIcon icon = new ImageIcon(imageBytes);
                        Image scaledImage = scaleImage(icon.getImage(), 1335, getHeight() - 125);
                        packageImages.put(packageName, new ImageIcon(scaledImage));

                        packages.add(packageName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                if (!packages.isEmpty()) {
                    startPackageCarousel(backgroundImage);
                } else {
                    imageOverlayLabel.setText("No packages available.");
                }
            }
        };
        worker.execute();
    }

    private void startPackageCarousel(JLabel backgroundImage) {
        javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (packages.size() > 0) {
                    String currentPackage = packages.get(currentPackageIndex);
                    imageOverlayLabel.setText(currentPackage);
                    backgroundImage.setIcon(packageImages.get(currentPackage));

                    currentPackageIndex = (currentPackageIndex + 1) % packages.size();
                }
            }
        });
        timer.start();
    }

    private Image scaleImage(Image img, int width, int height) {
        return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
