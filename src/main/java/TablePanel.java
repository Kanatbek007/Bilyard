import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TablePanel extends JPanel {
    private final TableManager tableManager;
    private final Map<Integer, JButton> tableButtons = new HashMap<>();

    public TablePanel() {
        setLayout(new GridLayout(4, 4, 10, 10));
        tableManager = new TableManager();

        for (int i = 1; i <= 16; i++) {
            JButton btn = new JButton("Стол " + i);
            btn.setBackground(Color.GREEN);
            int tableId = i;
            btn.addActionListener(e -> showTableMenu(tableId, btn));
            add(btn);
            tableButtons.put(i, btn);
        }
    }

    private void showTableMenu(int tableId, JButton btn) {
        // Реализация меню
    }
}