package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;
import java.time.Duration;

public class GetMinSessionTIme implements Function<List<SleepSession>, SleepAnalysisResult> {

    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(getMinSession(sleepSessions));
    }

    private SleepAnalysisResult formMessage(Long amount) {
        return new SleepAnalysisResult("Минимальная длительность сна в минутах за период", amount);
    }

    private Long getMinSession(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(SleepSession::getSleepDuration)
                .map(Duration::toMinutes)
                .min(Long::compareTo)
                .orElse(null);
    }
}
