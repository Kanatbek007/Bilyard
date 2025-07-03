package util;

public interface TableEventListener {
    void onTableOpened(int tableId);
    void onTablePaused(int tableId);
    void onTableClosed(int tableId);
}