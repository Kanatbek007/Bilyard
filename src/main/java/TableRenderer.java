import javax.swing.*;
import java.awt.*;

public class TableRenderer {
    public static void render(JButton button, TableState state) {
        button.setBackground(
                state.isRunning() ?
                        (state.isPaused() ? Color.ORANGE : Color.RED) :
                        Color.GREEN
        );
    }
}