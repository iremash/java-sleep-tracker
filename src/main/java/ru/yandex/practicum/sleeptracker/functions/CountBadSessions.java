package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;
import main.java.ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.util.List;
import java.util.function.Function;

public class CountBadSessions implements Function<List<SleepSession>, SleepAnalysisResult> {

    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(countBadSessions(sleepSessions));
    }

    private Long countBadSessions(List<SleepSession> sleepSessions) {
        return sleepSessions.stream()
                .map(SleepSession::getQuality)
                .filter(s -> s.equals(SleepQuality.BAD))
                .count();
    }

    private SleepAnalysisResult formMessage(Long result) {
        return new SleepAnalysisResult("Количество плохих ночей", result);
    }

}

