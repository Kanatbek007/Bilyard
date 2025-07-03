package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import util.TableState;

public class TableData implements TableState {
    private Date startTime;
    private Date pauseTime;
    private long pausedMillis = 0;
    private boolean isRunning = false;
    private boolean isPaused = false;

    private static final SimpleDateFormat TIME_FORMATTER =
            new SimpleDateFormat("HH:mm:ss");

    @Override
    public void startTimer() {
        if (!isRunning) {
            startTime = new Date();
            isRunning = true;
        } else if (isPaused) {
            pausedMillis += new Date().getTime() - pauseTime.getTime();
            isPaused = false;
        }
    }

    @Override
    public void pauseTimer() {

    }

    @Override
    public void stopTimer() {

    }

    @Override
    public long getElapsedTime() {
        return 0;
    }

    @Override
    public String getFormattedTime() {
        long elapsed = getElapsedTime() / 1000;
        return String.format("%02d:%02d:%02d",
                elapsed / 3600, (elapsed % 3600) / 60, elapsed % 60);
    }

    @Override
    public boolean isPaused() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}