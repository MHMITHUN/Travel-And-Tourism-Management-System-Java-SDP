package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class BookedHotelPanel extends JPanel {
    
    JPanel panel;
    JLabel l1;
    JTable table;
    String user;
    JScrollPane tableviewscroll;
    
    BookedHotelPanel() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        this.setSize(1278, 900);
        setLayout(null); 
        
        panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(7, 7, 1278, 160);
        add(panel);
        panel.setLayout(null);
        
        l1 = new JLabel("All Booked Hotels");
        l1.setIcon(null);
        l1.setBounds(5, 55, 400, 45);
        panel.add(l1);
        l1.setBackground(new Color(32, 178, 170));
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 30));
                
        tableviewscroll = new JScrollPane();
        tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableviewscroll.setBounds(7, 172, 1271, 617);
        for (Component c : tableviewscroll.getComponents()) {
            c.setBackground(Color.white);
        }
        add(tableviewscroll);
                  
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

        // Set a listener for window resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustColumnWidths();
            }
        });
        
        try {                                        
            Conn conn = new Conn();
            String sql = "select hotelname as 'Hotel Name', name as 'Customer Name', username as 'Username', persons as 'Total Persons', days as 'No. Of Days', ac_nonac as 'AC/Non-AC', food as 'Food Included', totalprice as 'Total Price' from bookhotel";
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

    public BookedHotelPanel(String user) {
        this();
        this.user = user;
        try {                                        
            Conn conn = new Conn();
            String sql = "select hotelname as 'Hotel Name', name as 'Customer Name', username as 'Username', persons as 'Total Persons', days as 'No. Of Days', ac_nonac as 'AC/Non-AC', food as 'Food Included', totalprice as 'Total Price' from bookhotel where username='" + user + "'";
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
        table.getColumnModel().getColumn(0).setMaxWidth(width / 7); // Hotel Name
        table.getColumnModel().getColumn(1).setMaxWidth(width / 5); // Customer Name
        table.getColumnModel().getColumn(2).setMaxWidth(width / 6); // Username
        table.getColumnModel().getColumn(3).setMaxWidth(width / 8); // Total Persons
        table.getColumnModel().getColumn(4).setMaxWidth(width / 10); // No. Of Days
        table.getColumnModel().getColumn(5).setMaxWidth(width / 8); // AC/Non-AC
        table.getColumnModel().getColumn(6).setMaxWidth(width / 8); // Food Included
        table.getColumnModel().getColumn(7).setMaxWidth(width / 7); // Total Price
    }
    
    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create a frame to hold the BookedHotelPanel
                JFrame frame = new JFrame("Travel Management System - Booked Hotels");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1300, 900);  // Set the size of the window
                frame.setLayout(new BorderLayout());

                // Create an instance of the BookedHotelPanel (you can change the user parameter here)
                BookedHotelPanel panel = new BookedHotelPanel();

                // Add the BookedHotelPanel to the frame
                frame.add(panel, BorderLayout.CENTER);

                // Make the frame visible
                frame.setVisible(true);
            }
        });
    }
}
