package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class GetAverageSleep implements Function<List<SleepSession>, SleepAnalysisResult> {

    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(getAverageSleep(sleepSessions));
    }

    private Double getAverageSleep(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(SleepSession::getSleepDuration)
                .map(Duration::toMinutes)
                .mapToLong(Long::longValue)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Нет данных"));
    }

    private SleepAnalysisResult formMessage(Double result) {
        return new SleepAnalysisResult("Среднее количество сна в минутах", result.intValue());
    }
}
