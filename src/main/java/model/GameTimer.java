// src/main/java/model/GameTimer.java
package model;

public class GameTimer {
    private long startTime;           // Время начала игры (System.currentTimeMillis())
    private long pauseStartTime;      // Время начала текущей паузы
    private long totalPauseDuration;  // Общая накопленная длительность пауз
    private boolean running;          // Флаг, запущен ли таймер (true, если идет игра, не на паузе)
    private boolean paused;           // Флаг, находится ли таймер на паузе

    public GameTimer() {
        reset();
    }

    public void start() {
        if (!running) { // Запускаем только если не запущен
            startTime = System.currentTimeMillis();
            running = true;
            paused = false;
            totalPauseDuration = 0; // Сброс при новом старте
            pauseStartTime = 0;
        } else if (paused) { // Если был на паузе, возобновляем
            resume();
        }
    }

    public void pause() {
        if (running && !paused) { // Пауза только если запущен и не на паузе
            pauseStartTime = System.currentTimeMillis();
            paused = true;
        }
    }

    public void resume() {
        if (running && paused) { // Возобновляем только если запущен и на паузе
            totalPauseDuration += (System.currentTimeMillis() - pauseStartTime);
            pauseStartTime = 0; // Сброс времени начала паузы
            paused = false;
        }
    }

    public void stop() {
        if (running) {
            running = false;
            paused = false;
            pauseStartTime = 0; // Сброс на всякий случай
        }
    }

    /**
     * Возвращает чистое время игры (без учета пауз) в миллисекундах.
     */
    public long getActiveGameDurationMillis() {
        if (!running) {
            return 0; // Таймер не запущен
        }

        long currentTotalDuration = System.currentTimeMillis() - startTime;
        long currentPauseCompensation = totalPauseDuration;

        if (paused) {
            // Если на паузе, добавляем текущую длительность этой паузы к компенсации
            currentPauseCompensation += (System.currentTimeMillis() - pauseStartTime);
        }

        return currentTotalDuration - currentPauseCompensation;
    }

    /**
     * Возвращает текущую длительность текущей паузы в миллисекундах.
     */
    public long getCurrentPauseDurationMillis() {
        if (running && paused && pauseStartTime > 0) {
            return System.currentTimeMillis() - pauseStartTime;
        }
        return 0;
    }

    public String getFormattedActiveGameTime() {
        long durationMillis = getActiveGameDurationMillis();
        long seconds = durationMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public String getFormattedCurrentPauseTime() {
        long durationMillis = getCurrentPauseDurationMillis();
        long seconds = durationMillis / 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        minutes %= 60; // На случай если пауза более часа, хотя маловероятно
        return String.format("%02d:%02d", minutes, seconds); // Часы для паузы обычно не нужны
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused() {
        return paused;
    }

    public void reset() {
        startTime = 0;
        pauseStartTime = 0;
        totalPauseDuration = 0;
        running = false;
        paused = false;
    }
}