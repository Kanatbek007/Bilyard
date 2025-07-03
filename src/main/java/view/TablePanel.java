package view;
import model.TableManager;
import util.TableStatus;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TablePanel extends JPanel {
    private final TableManager tableManager;
    private final Map<Integer, JButton> tableButtons = new HashMap<>();
    private static final int MAX_TABLES = 16;

    public TablePanel(TableManager manager) {
        this.tableManager = manager;
        setLayout(new BorderLayout());

        // Создаем контейнер для кнопок столов с прокруткой
        JPanel tablesContainer = new JPanel(new GridLayout(0, 4, 5, 5));
        tablesContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Добавляем столы
        for (int i = 1; i <= 8; i++) {
            JButton btn = createTableButton(i);
            tablesContainer.add(btn);
            tableButtons.put(i, btn);
        }

        JScrollPane scrollPane = new JScrollPane(tablesContainer);
        add(scrollPane, BorderLayout.CENTER);

        setBackground(new Color(40, 40, 40));
        tablesContainer.setBackground(new Color(40, 40, 40));
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
    }

    private JButton createTableButton(int tableId) {
        JButton btn = new JButton("Стол " + tableId);
        btn.setPreferredSize(new Dimension(120, 80));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.addActionListener(e -> tableManager.openTable(tableId));

        updateButtonAppearance(btn, TableStatus.FREE);
        return btn;
    }

    public void updateTableButton(int tableId) {
        JButton btn = tableButtons.get(tableId);
        TableStatus status = tableManager.getTableStatus(tableId);
        updateButtonAppearance(btn, status);
    }

    private void updateButtonAppearance(JButton btn, TableStatus status) {
        switch (status) {
            case FREE:
                btn.setBackground(new Color(70, 130, 180)); // SteelBlue
                btn.setForeground(Color.WHITE);
                break;
            case ACTIVE:
                btn.setBackground(new Color(50, 205, 50)); // LimeGreen
                btn.setForeground(Color.BLACK);
                break;
            case PAUSED:
                btn.setBackground(new Color(255, 215, 0)); // Gold
                btn.setForeground(Color.BLACK);
                break;
        }
    }
}