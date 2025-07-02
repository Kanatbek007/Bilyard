import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class TablePanel extends JPanel {
    private final TableManager tableManager;
    private final Map<Integer, JButton> tableButtons = new HashMap<>();

    public TablePanel() {
        setLayout(new GridLayout(4, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tableManager = new TableManager();

        for (int i = 1; i <= 16; i++) {
            JButton btn = createTableButton(i);
            add(btn);
            tableButtons.put(i, btn);
        }
    }

    private JButton createTableButton(int tableId) {
        // Загрузка изображения
        ImageIcon originalIcon = TableImageLoader.loadTableIcon(getClass(), tableId);

        // Создание кнопки с кастомной отрисовкой
        JButton btn = new JButton("Стол " + tableId) {
            @Override
            protected void paintComponent(Graphics g) {
                // Отрисовка фона
                super.paintComponent(g);

                if (originalIcon != null) {
                    // Вычисление пропорций
                    double widthRatio = (double)getWidth() / originalIcon.getIconWidth();
                    double heightRatio = (double)getHeight() / originalIcon.getIconHeight();
                    double ratio = Math.min(widthRatio, heightRatio);

                    // Новые размеры с сохранением пропорций
                    int newWidth = (int)(originalIcon.getIconWidth() * ratio);
                    int newHeight = (int)(originalIcon.getIconHeight() * ratio);

                    // Позиционирование по центру
                    int x = (getWidth() - newWidth) / 2;
                    int y = (getHeight() - newHeight) / 2;

                    // Отрисовка изображения
                    g.drawImage(originalIcon.getImage(), x, y, newWidth, newHeight, this);
                }
            }
        };

        // Настройка внешнего вида
        btn.setBackground(AppColors.ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.GREEN_DARK, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);

        // Обработчик клика
        btn.addActionListener(e -> TableMenuManager.showTableMenu(TablePanel.this, tableId));

        return btn;
    }
}