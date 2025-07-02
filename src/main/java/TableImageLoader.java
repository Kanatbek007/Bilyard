import javax.swing.*;

public class TableImageLoader {
    public static ImageIcon loadTableIcon(Class<?> context, int tableId) {
        try {
            return new ImageIcon(context.getResource("/icons/table" + tableId + ".png"));
        } catch (Exception e) {
            System.err.println("Ошибка загрузки иконки для стола " + tableId);
            return null;
        }
    }
}