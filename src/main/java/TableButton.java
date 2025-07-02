import javax.swing.*;
import java.awt.*;

public class TableButton extends JButton {
    private final ImageIcon originalIcon;

    public TableButton(int tableId, ImageIcon icon) {
        super("Стол " + tableId);
        this.originalIcon = icon;

        setBackground(AppColors.ACCENT);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFocusPainted(false);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GREEN_DARK, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        setContentAreaFilled(false);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (originalIcon != null) {
            double widthRatio = (double)getWidth() / originalIcon.getIconWidth();
            double heightRatio = (double)getHeight() / originalIcon.getIconHeight();
            double ratio = Math.min(widthRatio, heightRatio);

            int newWidth = (int)(originalIcon.getIconWidth() * ratio);
            int newHeight = (int)(originalIcon.getIconHeight() * ratio);

            int x = (getWidth() - newWidth) / 2;
            int y = (getHeight() - newHeight) / 2;

            g.drawImage(originalIcon.getImage(), x, y, newWidth, newHeight, this);
        }
    }
}