import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TablePanel extends JPanel {
    private final TableManager tableManager;
    private final Map<Integer, JButton> tableButtons = new HashMap<>();
    private int tableCounter = 1;
    private JPanel tablesContainer;
    private static final int MAX_TABLES = 16;

    public TablePanel() {
        setLayout(new BorderLayout());
        tableManager = new TableManager();

        // 1. Создаем верхнюю панель управления
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // 2. Создаем контейнер для кнопок столов с прокруткой
        tablesContainer = new JPanel(new GridLayout(0, 4, 5, 5));
        tablesContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(tablesContainer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // 3. Добавляем начальные 16 столов
        for (int i = 0; i < 4; i++) { // Начинаем с 4 столов вместо 16
            addTable();
        }
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.LIGHT_GRAY);

        // Кнопка добавления стола
        JButton addButton = new JButton("Добавить стол");
        addButton.addActionListener(e -> {
            if (tableCounter <= MAX_TABLES) {
                addTable();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Максимальное количество столов (" + MAX_TABLES + ") достигнуто",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Кнопка удаления стола
        JButton removeButton = new JButton("Удалить стол");
        removeButton.addActionListener(e -> {
            if (tableCounter > 1) { // Нельзя удалить первый стол (tableId = 1)
                removeTable();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Нельзя удалить первый стол",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Настройка кнопок
        for (JButton btn : new JButton[]{addButton, removeButton}) {
            btn.setPreferredSize(new Dimension(120, 30));
            btn.setFocusPainted(false);
        }

        panel.add(addButton);
        panel.add(removeButton);

        return panel;
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

    private void removeTable() {
        if (tableCounter <= 1) return; // Не удаляем первый стол

        int lastId = --tableCounter;
        tablesContainer.remove(tableButtons.remove(lastId));
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

        // Уменьшенные размеры и настройки
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