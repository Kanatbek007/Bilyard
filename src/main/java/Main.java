import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TablePanel tablePanel = new TablePanel();
            JFrame frame = new JFrame("Управление столами");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(tablePanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}