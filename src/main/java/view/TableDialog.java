package view;
import model.TableManager;
import util.TableStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TableDialog extends JDialog {
    private final int tableId;
    private final TableManager tableManager;

    public TableDialog(int tableId, boolean isNewSession, TableManager manager) {
        this.tableId = tableId;
        this.tableManager = manager;

        setTitle("Управление столом #" + tableId);
        setSize(300, 150);
        setLayout(new FlowLayout());
        setModal(true);
        setLocationRelativeTo(null);

        if (isNewSession) {
            setupNewSessionDialog();
        } else {
            setupExistingSessionDialog();
        }
    }

    private void setupNewSessionDialog() {
        add(new JLabel("Стол #" + tableId + " начал игру"));

        JButton openBtn = new JButton("Открыть");
        openBtn.addActionListener(this::handleOpen);
        add(openBtn);
    }

    private void setupExistingSessionDialog() {
        add(new JLabel("Управление игрой на столе #" + tableId));

        JButton pauseBtn = new JButton("Пауза");
        pauseBtn.addActionListener(this::handlePause);

        JButton finishBtn = new JButton("Завершить");
        finishBtn.addActionListener(this::handleFinish);

        // Проверяем текущее состояние стола
        if (tableManager.getTableStatus(tableId) == TableStatus.PAUSED) {
            pauseBtn.setEnabled(false);
        }

        add(pauseBtn);
        add(finishBtn);
    }

    private void handleOpen(ActionEvent e) {
        tableManager.openTable(tableId);
        dispose();
    }

    private void handlePause(ActionEvent e) {
        tableManager.pauseTable(tableId);
        dispose();
    }

    private void handleFinish(ActionEvent e) {
        tableManager.finishTable(tableId);
        dispose();
    }
}