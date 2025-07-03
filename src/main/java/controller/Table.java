package controller;
import util.TableStatus;

import java.time.Duration;
import java.time.Instant;

public class Table {
    private final int id;
    private TableStatus status;
    private Instant startTime;
    private Instant pauseTime;
    private Duration pauseDuration;

    public Table(int id) {
        this.id = id;
        this.status = TableStatus.FREE;
        this.pauseDuration = Duration.ZERO;
    }

    public void startSession() {
        this.status = TableStatus.ACTIVE;
        this.startTime = Instant.now();
        this.pauseDuration = Duration.ZERO;
    }

    public void pauseSession() {
        if (status == TableStatus.ACTIVE) {
            this.status = TableStatus.PAUSED;
            this.pauseTime = Instant.now();
        }
    }

    public void resumeSession() {
        if (status == TableStatus.PAUSED) {
            this.status = TableStatus.ACTIVE;
            this.pauseDuration = pauseDuration.plus(Duration.between(pauseTime, Instant.now()));
        }
    }

    public void endSession() {
        this.status = TableStatus.FREE;
    }

    public long getSessionDuration() {
        if (status == TableStatus.FREE) {
            return 0;
        }

        Duration duration = Duration.between(startTime, Instant.now()).minus(pauseDuration);
        return duration.getSeconds();
    }

    public int getId() {
        return id;
    }

    public TableStatus getStatus() {
        return status;
    }
}