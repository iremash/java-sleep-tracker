package main.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SleepSession {
    private final LocalDateTime startDate;
    private final LocalDateTime finishDate;
    private final SleepQuality quality;
    private final Duration sleepDuration;
    private final LocalTime startTime;
    private final LocalTime finishTime;

    public SleepSession(LocalDateTime startDate, LocalDateTime finishDate, SleepQuality quality) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.quality = quality;
        sleepDuration = Duration.between(startDate, finishDate);
        startTime = startDate.toLocalTime();
        finishTime = finishDate.toLocalTime();

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public Duration getSleepDuration() {
        return sleepDuration;
    }
}
