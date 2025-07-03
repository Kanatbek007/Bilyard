package util;

public interface TableState {
    void startTimer();
    void pauseTimer();
    void stopTimer();
    long getElapsedTime();
    String getFormattedTime();
    boolean isPaused();
    boolean isRunning();
}