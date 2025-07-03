package app;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

import view.TablePanel;
import view.ConfirmExitDialog;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            TablePanel tablePanel = new TablePanel();

            JFrame frame = new JFrame("Bilyard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            frame.add(tablePanel, BorderLayout.CENTER);

            ConfirmExitDialog exitDialog = new ConfirmExitDialog(frame);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}