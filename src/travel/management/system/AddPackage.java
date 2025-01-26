package travel.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.sql.*;

public class AddPackage extends JFrame implements ActionListener{
    
    JPanel headerpanel;
    JTextField t1, t2, t3, t4;
    JTextArea ta1;
    JLabel l1, l2, l3, l4, l5, l6, l7, filename;
    JButton b1, b2, b3;
    File file;
    PackagePanel pp;
    PackageDetails pd;
    String originalPlace; // To store the original place name for updates
    byte[] photo = null; // Class-level photo variable
   
    AddPackage(){
        
        setSize(750, 650);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        headerpanel = new JPanel();
        headerpanel.setBackground(new Color(32, 178, 170));
        headerpanel.setBounds(0, 0, 750, 50);
        add(headerpanel);
        headerpanel.setLayout(null);
        
        l1 = new JLabel("Add Package");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setBounds(0, 2, 750, 50);
        headerpanel.add(l1);
        
        l2 = new JLabel("Place Name:");
        l2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l2.setBounds(60, 80, 200, 40);
        add(l2);
        
        l3 = new JLabel();
        l3.setBorder(new LineBorder(Color.GRAY));
        l3.setBounds(60, 375, 220, 220);
        Image i1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/destination.png")).getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH);
        l3.setIcon(new ImageIcon(i1));
        add(l3);
        
        l4 = new JLabel("City/State Name:");
        l4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l4.setBounds(60, 130, 200, 40);
        add(l4);
        
        l5 = new JLabel("Price:");
        l5.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l5.setBounds(60, 180, 200, 40);
        add(l5);
        
        l6 = new JLabel("No. Of Day And Night:");
        l6.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l6.setBounds(60, 230, 200, 40);
        add(l6);
        
        l7 = new JLabel("Description:");
        l7.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        l7.setBounds(60, 280, 200, 40);
        add(l7);
        
        t1 = new JTextField();
        t1.setForeground(Color.DARK_GRAY);
        t1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t1.setBounds(270, 80, 380, 40);
        add(t1);
        
        t2 = new JTextField();
        t2.setForeground(Color.DARK_GRAY);
        t2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t2.setBounds(270, 130, 380, 40);
        add(t2);
        
        t3 = new JTextField();
        t3.setForeground(Color.DARK_GRAY);
        t3.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t3.setBounds(270, 180, 380, 40);
        add(t3);
        
        t4 = new JTextField();
        t4.setForeground(Color.DARK_GRAY);
        t4.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        t4.setBounds(270, 230, 380, 40);
        add(t4);
        
        ta1 =  new JTextArea();
        ta1.setForeground(Color.DARK_GRAY);
        ta1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        ta1.setBounds(270, 280, 380, 85);
        ta1.setBorder(new LineBorder(Color.GRAY));
        ta1.setLineWrap(true);
        add(ta1);
        
        
        b1 = new JButton("Choose File");
        b1.addActionListener(this);
        b1.setFocusable(false);
        b1.setBackground(new Color(245, 245, 245));
        b1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        b1.setBounds(290, 465, 110, 30);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(b1);
        
        b2 = new JButton("Add");
        b2.setBorder(new EmptyBorder(0, 0, 0, 0));
        b2.setForeground(new Color(255, 255, 255));
        b2.setBackground(new Color(32, 178, 170));
        b2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b2.addActionListener(this);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setBounds(578, 560, 140, 38);
        b2.setFocusable(false);
        add(b2);
        
        b3 = new JButton("Update");
        b3.setBorder(new EmptyBorder(0, 0, 0, 0));
        b3.setForeground(new Color(255, 255, 255));
        b3.setBackground(new Color(32, 178, 170));
        b3.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b3.addActionListener(this);
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setBounds(578, 560, 140, 38);
        b3.setFocusable(false);
        b3.setVisible(false);
        add(b3);
       
        filename = new JLabel("No file chosen");
        filename.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filename.setBounds(410, 463, 550, 32);
        add(filename);
    }
    
    // Constructor for adding a new package
    public AddPackage(PackagePanel pp){
        this();
        this.pp = pp;
    } 
    
    // Constructor for editing an existing package
    public AddPackage(PackageDetails pd, String place){
        this();
        this.pd = pd;
        this.originalPlace = place; // Store the original place name
        l1.setText("Edit Package");
        b2.setVisible(false);
        b3.setVisible(true);
        try{
            Conn conn = new Conn();
            String sql = "SELECT * FROM package WHERE place = ?";
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ps.setString(1, place);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                // Make the Place Name field editable
                // Removed the lines that made it non-editable
                // t1.setEditable(false);
                // t1.setBackground(Color.WHITE);
                
                t1.setText(rs.getString("place")); // Assuming column name is 'place'
                t2.setText(rs.getString("state")); // Adjust column names accordingly
                t3.setText(rs.getString("price"));
                t4.setText(rs.getString("days_nights"));
                ta1.setText(rs.getString("description"));
                photo = rs.getBytes("image"); // Assign to class-level 'photo'
                if(photo != null){
                    Image img = ImageIO.read(new ByteArrayInputStream(photo)).getScaledInstance(220, 220, Image.SCALE_SMOOTH);
                    l3.setIcon(new ImageIcon(img));
                    filename.setText("Existing Image");
                } else {
                    filename.setText("No image available");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading package details.");
        }    
         
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == b1)
        {

            FileDialog fd = new FileDialog(this, "Choose an Image", FileDialog.LOAD);
            fd.setDirectory(System.getProperty("user.home"));
            fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif");
            fd.setVisible(true);
            String strfilename = fd.getFile();
            if(strfilename != null) 
            {
                String imagepath = fd.getDirectory() + strfilename;
                file = new File(imagepath);
                try{
                    Image image = ImageIO.read(file).getScaledInstance(220, 220, Image.SCALE_SMOOTH);
                    l3.setIcon(new ImageIcon(image));
                    filename.setText(file.getName());
                    
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    for(int readNum; (readNum = fis.read(buf)) != -1;){
                        bos.write(buf, 0, readNum);
                    }
                    photo = bos.toByteArray(); // Update the class-level 'photo'
                } catch(Exception ae){
                    ae.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading image.");
                }
            }
        }
        else if (e.getSource() == b2){
            // Add new package
            try {
                // Input validation
                if(t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() ||
                   t3.getText().trim().isEmpty() || t4.getText().trim().isEmpty() ||
                   ta1.getText().trim().isEmpty() || photo == null){
                    JOptionPane.showMessageDialog(null, "Please fill all fields and select an image.");
                    return;
                }
                
                Conn conn = new Conn();
                String sql = "INSERT INTO package (place, state, price, days_nights, description, image) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.c.prepareStatement(sql);
                ps.setString(1, t1.getText());
                ps.setString(2, t2.getText());
                ps.setString(3, t3.getText());
                ps.setString(4, t4.getText());
                ps.setString(5, ta1.getText());
                ps.setBytes(6, photo);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Package Created Successfully");
                if(pp != null){
                    pp.createtablemodel();
                }
                this.dispose();
            } catch (SQLIntegrityConstraintViolationException sie){
                sie.printStackTrace();
                JOptionPane.showMessageDialog(null, "Place Name already exists. Please choose a different name.");
            } catch (Exception ae){
                ae.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding package.");
            }
        }
        else if (e.getSource() == b3){
            // Update existing package
            try {
                // Input validation
                if(t1.getText().trim().isEmpty() || t2.getText().trim().isEmpty() ||
                   t3.getText().trim().isEmpty() || t4.getText().trim().isEmpty() ||
                   ta1.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }
                
                Conn conn =  new Conn();
                String sql1 = "UPDATE package SET place = ?, state = ?, price = ?, days_nights = ?, description = ?, image = ? WHERE place = ?";
                PreparedStatement ps1 = conn.c.prepareStatement(sql1);
                ps1.setString(1, t1.getText()); // New place name
                ps1.setString(2, t2.getText());
                ps1.setString(3, t3.getText());
                ps1.setString(4, t4.getText());
                ps1.setString(5, ta1.getText());
                ps1.setBytes(6, photo); // This will be either the existing photo or the new one if selected
                ps1.setString(7, originalPlace); // Use the original place name in WHERE clause
                ps1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Package Updated Successfully");
                if(pd != null){
                    pd.display();
                }
                this.dispose();
            } catch (SQLIntegrityConstraintViolationException sie){
                sie.printStackTrace();
                JOptionPane.showMessageDialog(null, "Place Name already exists. Please choose a different name.");
            } catch (Exception ae){
                ae.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating package.");
            }
        }
    }
}
