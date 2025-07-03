package model;
import controller.Table;
import util.TableStatus;
import view.TableDialog;
import view.TablePanel;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class TableManager {
    private final Map<Integer, Table> tables = new HashMap<>();
    private final TablePanel tablePanel;

    public TableManager() {
        this.tablePanel = new TablePanel(this);
        initializeTables();
    }

    private void initializeTables() {
        for (int i = 1; i <= 8; i++) {
            tables.put(i, new Table(i));
        }
    }

    public void openTable(int tableId) {
        Table table = tables.get(tableId);
        if (table.getStatus() == TableStatus.FREE) {
            table.startSession();
            tablePanel.updateTableButton(tableId);
            showTableDialog(tableId, true);
        } else {
            showTableDialog(tableId, false);
        }
    }

    public void pauseTable(int tableId) {
        tables.get(tableId).pauseSession();
        tablePanel.updateTableButton(tableId);
    }

    public void finishTable(int tableId) {
        Table table = tables.get(tableId);
        table.endSession();
        tablePanel.updateTableButton(tableId);
        showSummaryDialog(tableId, table.getSessionDuration());
    }

    private void showTableDialog(int tableId, boolean isNewSession) {
        TableDialog dialog = new TableDialog(tableId, isNewSession, this);
        dialog.setVisible(true);
    }

    private void showSummaryDialog(int tableId, long duration) {
        // Реализация диалога с итогами
        long hours = duration / 3600;
        long minutes = (duration % 3600) / 60;
        String time = String.format("%02d:%02d", hours, minutes);

        JOptionPane.showMessageDialog(tablePanel,
                "Стол #" + tableId + "\nВремя игры: " + time + "\nСумма: " + calculateSum(duration) + " руб.",
                "Игра завершена",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private double calculateSum(long duration) {
        // Логика расчета суммы
        return duration * 5.0 / 60; // 5 руб за минуту
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public TableStatus getTableStatus(int tableId) {
        return tables.get(tableId).getStatus();
    }
}