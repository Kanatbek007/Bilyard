package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import model.TableManager;
import util.AppColors;
import util.TableImageLoader;

public class TablePanel extends JPanel {
    private final TableManager tableManager;
    private final Map<Integer, JButton> tableButtons = new HashMap<>();
    private int tableCounter = 1;
    private JPanel tablesContainer;
    private static final int MAX_TABLES = 16;

    public TablePanel() {
        setLayout(new BorderLayout());
        tableManager = new TableManager();

        // Создаем контейнер для кнопок столов с прокруткой
        tablesContainer = new JPanel(new GridLayout(0, 4, 5, 5));
        tablesContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(tablesContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        for (int i = 0; i < 16; i++) {
            addTable();
        }
        setBackground(new Color(0, 0, 0));
        setOpaque(true);

        tablesContainer.setBackground(new Color(40, 40, 40));
        tablesContainer.setOpaque(true);

        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        scrollPane.setBorder(null);
    }

    private void addTable() {
        if (tableCounter > MAX_TABLES) return;

        int tableId = tableCounter++;
        JButton btn = createTableButton(tableId);
        tablesContainer.add(btn);
        tableButtons.put(tableId, btn);
        revalidate();
        repaint();
    }

    private JButton createTableButton(int tableId) {
        ImageIcon originalIcon = TableImageLoader.loadTableIcon(getClass(), tableId);

        JButton btn = new JButton("Стол " + tableId) {
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
        };

        btn.setPreferredSize(new Dimension(100, 80));
        btn.setBackground(AppColors.ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);

        btn.addActionListener(e -> TableMenuManager.showTableMenu(TablePanel.this, tableId));

        return btn;
    }
}