package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class BackgroundUtil {

    public static void setBackgroundImage(JPanel panel, String imagePath) {
        panel.setLayout(new BorderLayout());
        JLayeredPane layeredPane = new JLayeredPane();
        panel.add(layeredPane, BorderLayout.CENTER);

        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(null);
        content.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        layeredPane.add(content, JLayeredPane.PALETTE_LAYER);

        panel.setLayout(new BorderLayout());
        panel.add(content);
        panel.revalidate();
        panel.repaint();
    }
}
