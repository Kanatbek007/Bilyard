package view;

import javax.swing.*;

public class TableMenuManager {
    public static void showTableMenu(JPanel parent, int tableId) {
        JOptionPane.showMessageDialog(parent,
                "Выбрано: Стол " + tableId,
                "Меню стола",
                JOptionPane.INFORMATION_MESSAGE);
    }
}