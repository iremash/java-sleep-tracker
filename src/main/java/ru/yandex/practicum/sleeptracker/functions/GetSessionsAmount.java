package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class GetSessionsAmount implements Function<List<SleepSession>, SleepAnalysisResult> {

    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(sleepSessions.size());
    }

    private SleepAnalysisResult formMessage(Integer amount) {
        return new SleepAnalysisResult("Количество сессий сна за период", amount);
    }

}