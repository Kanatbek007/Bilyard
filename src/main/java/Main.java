import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TablePanel tablePanel = new TablePanel();

            JFrame frame = new JFrame("Bilyard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            frame.add(tablePanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            JButton closeButton = new JButton("Закрыть программу");

            ConfirmExitDialog exitDialog = new ConfirmExitDialog(frame);

            closeButton.addActionListener(e -> exitDialog.showDialog());

            buttonPanel.add(closeButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
