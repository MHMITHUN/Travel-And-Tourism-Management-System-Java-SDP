package travel.management.system;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

public class PDFGenerator {
    
    private static void addImageToPDF(Document document, String imagePath) throws DocumentException, IOException {
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            // Skip silently if image doesn't exist
            return;
        }
        
        try {
            Image image = Image.getInstance(imagePath);
            // Scale image to fit the page width while maintaining aspect ratio
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / image.getWidth()) * 100;
            image.scalePercent(scaler);
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
            document.add(new Paragraph(" ")); // Add some spacing after image
        } catch (Exception e) {
            // Skip silently if there's any error with the image
            return;
        }
    }
    
    public static void generateHotelBookingReceipt(String username, String hotelName, String persons, 
            String days, String ac, String food, String totalCost) {
        Document document = null;
        try {
            // Create directory if it doesn't exist
            java.io.File dir = new java.io.File("receipts");
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            String fileName = "receipts/" + username + "_" + hotelName + "_" + 
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Add header
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph header = new Paragraph("Travel and Tourism Management System", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            // Add spacing
            document.add(new Paragraph(" "));
            
            // Try to add hotel image
            String imagePath = "src/travel/management/system/icons/hotels/" + hotelName.toLowerCase().replace(" ", "_") + ".jpg";
            addImageToPDF(document, imagePath);

            // Add booking details with styled fonts
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            
            document.add(new Paragraph("Hotel Booking Receipt", titleFont));
            document.add(new Paragraph("Date: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()), normalFont));
            document.add(new Paragraph("Customer Name: " + username, normalFont));
            document.add(new Paragraph("Hotel Name: " + hotelName, normalFont));
            document.add(new Paragraph("Number of Persons: " + persons, normalFont));
            document.add(new Paragraph("Number of Days: " + days, normalFont));
            document.add(new Paragraph("AC/Non-AC: " + ac, normalFont));
            document.add(new Paragraph("Food Included: " + food, normalFont));
            document.add(new Paragraph(" "));
            
            // Add total cost with different styling
            Font costFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.RED);
            Paragraph costPara = new Paragraph("Total Cost: Rs. " + totalCost, costFont);
            costPara.setAlignment(Element.ALIGN_RIGHT);
            document.add(costPara);
            
            // Add footer
            document.add(new Paragraph(" "));
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
            Paragraph footer = new Paragraph("Thank you for choosing our service!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            javax.swing.JOptionPane.showMessageDialog(null, "PDF Receipt generated successfully!\nSaved as: " + fileName);
            
        } catch (DocumentException | IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
        }
    }

    public static void generatePackageBookingReceipt(String username, String packageName, 
            String persons, String startDate, String endDate, String totalCost) {
        Document document = null;
        try {
            // Create directory if it doesn't exist
            java.io.File dir = new java.io.File("receipts");
            if (!dir.exists()) {
                dir.mkdir();
            }
            
            String fileName = "receipts/" + username + "_" + packageName + "_" + 
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Add header
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Paragraph header = new Paragraph("Travel and Tourism Management System", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            // Add spacing
            document.add(new Paragraph(" "));
            
            // Try to add package/destination image
            String imagePath = "src/travel/management/system/icons/destinations/" + packageName.toLowerCase().replace(" ", "_") + ".jpg";
            addImageToPDF(document, imagePath);

            // Add booking details with styled fonts
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            
            document.add(new Paragraph("Package Booking Receipt", titleFont));
            document.add(new Paragraph("Date: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()), normalFont));
            document.add(new Paragraph("Customer Name: " + username, normalFont));
            document.add(new Paragraph("Package Name: " + packageName, normalFont));
            document.add(new Paragraph("Number of Persons: " + persons, normalFont));
            document.add(new Paragraph("Start Date: " + startDate, normalFont));
            document.add(new Paragraph("End Date: " + endDate, normalFont));
            document.add(new Paragraph(" "));
            
            // Add total cost with different styling
            Font costFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.RED);
            Paragraph costPara = new Paragraph("Total Cost: Rs. " + totalCost, costFont);
            costPara.setAlignment(Element.ALIGN_RIGHT);
            document.add(costPara);
            
            // Add footer
            document.add(new Paragraph(" "));
            Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
            Paragraph footer = new Paragraph("Thank you for choosing our service!", footerFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            javax.swing.JOptionPane.showMessageDialog(null, "PDF Receipt generated successfully!\nSaved as: " + fileName);
            
        } catch (DocumentException | IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error generating PDF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
        }
    }
}
