package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class AllCustomerPanel extends JPanel {
    
    JPanel panel;
    JLabel l1;
    JTable table;
    JScrollPane tableviewscroll;
    
    AllCustomerPanel() {
        
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        this.setSize(1278, 900);
        setLayout(null); 
        
        // Panel for the header
        panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(7, 7, 1278, 160);
        add(panel);
        panel.setLayout(null);
        
        // Header label
        l1 = new JLabel("All Customers");
        l1.setIcon(null);
        l1.setBounds(5, 55, 224, 45);
        panel.add(l1);
        l1.setBackground(new Color(32, 178, 170));
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 30));
                
        // Table scroll panel
        tableviewscroll = new JScrollPane();
        tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableviewscroll.setBounds(7, 172, 1271, 617);
        for (Component c : tableviewscroll.getComponents()) {
            c.setBackground(Color.white);
        }
        add(tableviewscroll);
        
        // Table setup
        table = new JTable();
        table.setBorder(new LineBorder(Color.LIGHT_GRAY));
        table.getTableHeader().setBackground(new Color(32,178,170));
        table.getTableHeader().setForeground(Color.white);
        table.setSelectionBackground(new Color(240, 255, 255));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.getTableHeader().setPreferredSize(new Dimension(50, 40));
        table.setFocusable(false);
        table.setDragEnabled(false);
        table.setRowHeight(40);
        table.setDefaultEditor(Object.class, null);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);
        tableviewscroll.setViewportView(table);
        
        // Add component listener for resizing event
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustColumnWidths();
            }
        });
        
        // Fetch data from database
        try {                                      
            Conn conn = new Conn();
            String sql = "select name as 'Customer Name', username as 'Username', password as 'Password', nid as 'NID No', phone as 'Phone No ', email as 'Email Id' from customer";
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            adjustColumnWidths(); // Adjust column widths based on the initial window size
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }
    
    // Method to adjust column widths dynamically
    private void adjustColumnWidths() {
        // Get the width of the tableviewscroll panel
        int width = tableviewscroll.getWidth();

        // Set column widths based on the current window size
        int customerNameWidth = Math.max(width / 4, 150); // Customer Name (wider portion)
        int usernameWidth = Math.max(width / 6, 100);
        int passwordWidth = Math.max(width / 7, 100);
        
        // Decrease the width of NID and Phone columns
        int nidWidth = Math.max(width / 6, 90); // Reduced width for NID No
        int phoneWidth = Math.max(width / 7, 90); // Reduced width for Phone No
        
        // Increase the width of Email column
        int emailWidth = Math.max(width / 5, 150); // Increased width for Email Id

        // Apply the calculated widths
        table.getColumnModel().getColumn(0).setMaxWidth(customerNameWidth); // Customer Name
        table.getColumnModel().getColumn(1).setMaxWidth(usernameWidth); // Username
        table.getColumnModel().getColumn(2).setMaxWidth(passwordWidth); // Password
        table.getColumnModel().getColumn(3).setMaxWidth(nidWidth); // NID No (smaller width)
        table.getColumnModel().getColumn(4).setMaxWidth(phoneWidth); // Phone No (smaller width)
        table.getColumnModel().getColumn(5).setMaxWidth(emailWidth); // Email Id (wider width)
    }
    
    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create a frame to hold the AllCustomerPanel
                JFrame frame = new JFrame("Travel Management System - All Customers");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1300, 900);  // Set the size of the window
                frame.setLayout(new BorderLayout());

                // Create an instance of the AllCustomerPanel
                AllCustomerPanel panel = new AllCustomerPanel();

                // Add the AllCustomerPanel to the frame
                frame.add(panel, BorderLayout.CENTER);

                // Make the frame visible
                frame.setVisible(true);
            }
        });
    }
}
