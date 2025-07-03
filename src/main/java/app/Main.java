package app;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

import model.TableManager;
import view.TablePanel;
import view.ConfirmExitDialog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TableManager tableManager = new TableManager();

            JFrame frame = new JFrame("Управление столами");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(tableManager.getTablePanel());
            frame.setVisible(true);
        });
    }
}