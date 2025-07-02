import java.util.HashMap;
import java.util.Map;

public class TableManager {
    private final Map<Integer, TableData> tables = new HashMap<>();

    public TableManager() {
        for (int i = 1; i <= 16; i++) {
            tables.put(i, new TableData());
        }
    }

    public void openTable(int tableId) {
        tables.get(tableId).startTimer();
    }
}