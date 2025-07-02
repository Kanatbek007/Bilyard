import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class ThemeSwitcher {
    private static boolean darkTheme = true;

    public static JButton createThemeToggleButton(JFrame frame) {
        JButton themeButton = new JButton("Светлая тема");
        themeButton.setPreferredSize(new Dimension(120, 30));

        // Установка начального стиля кнопки в зависимости от текущей темы
        updateButtonStyle(themeButton);

        themeButton.addActionListener(e -> {
            darkTheme = !darkTheme;
            try {
                if (darkTheme) {
                    FlatDarkLaf.setup();
                    themeButton.setText("Светлая тема");
                } else {
                    FlatLightLaf.setup();
                    themeButton.setText("Тёмная тема");
                }
                updateButtonStyle(themeButton);
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return themeButton;
    }

    private static void updateButtonStyle(JButton button) {
        if (darkTheme) {
            button.setBackground(new Color(50, 50, 50));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        } else {
            button.setBackground(new Color(240, 240, 240));
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        }
    }
}