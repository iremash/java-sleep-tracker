package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;
import main.java.ru.yandex.practicum.sleeptracker.enums.BirdType;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


public class GetBirdType implements Function<List<SleepSession>, SleepAnalysisResult> {
    private final LocalTime EARLY_BIRD_START_TIME = LocalTime.of(22, 0);
    private final LocalTime EARLY_BIRD_FINISH_TIME = LocalTime.of(7, 0);
    private final LocalTime NIGHT_OWL_START_TIME = LocalTime.of(23, 0);
    private final LocalTime NIGHT_OWL_FINISH_TIME = LocalTime.of(9, 0);

    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(getBirdType(sleepSessions));
    }

    private SleepAnalysisResult formMessage(BirdType type) {
        return new SleepAnalysisResult("Ваш тип спящего", BirdType.toString(type));
    }

    private BirdType getBirdType(List<SleepSession> sleepSessions) {
        List<BirdType> types = sleepSessions.stream()
                .map(this::countBirdType)
                .toList();

        return types.stream()
                .max(Comparator.comparingInt(n -> Collections.frequency(types, n)))
                .orElse(BirdType.DOVE);
    }

    private BirdType countBirdType(SleepSession s) {
        if (s.getStartTime().isBefore(EARLY_BIRD_START_TIME) &&
                s.getFinishTime().isBefore(EARLY_BIRD_FINISH_TIME)) {
            return BirdType.EARLY_BIRD;
        } else if (s.getStartTime().isAfter(NIGHT_OWL_START_TIME) &&
                s.getStartTime().isAfter(NIGHT_OWL_FINISH_TIME)) {
            return BirdType.NIGHT_OWL;
        }
        return BirdType.DOVE;
    }


}


