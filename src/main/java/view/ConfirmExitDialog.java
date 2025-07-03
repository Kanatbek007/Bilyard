package view;

import javax.swing.*;

public class ConfirmExitDialog {

    private final JFrame parentFrame;

    public ConfirmExitDialog(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void showDialog() {
        int result = JOptionPane.showConfirmDialog(
                parentFrame,
                "Закрыть программу?",
                "Выход",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
