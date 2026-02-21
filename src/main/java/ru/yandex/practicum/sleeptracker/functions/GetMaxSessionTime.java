package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class GetMaxSessionTime implements Function<List<SleepSession>, SleepAnalysisResult> {

    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(getMaxSession(sleepSessions));
    }

    private SleepAnalysisResult formMessage(Long amount) {
        return new SleepAnalysisResult("Максимальная длительность сна в минутах за период", amount);
    }

    private Long getMaxSession(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(SleepSession::getSleepDuration)
                .map(Duration::toMinutes)
                .max(Long::compareTo)
                .orElse(null);
    }
}