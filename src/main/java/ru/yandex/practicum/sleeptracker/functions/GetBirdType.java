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
    private final LocalTime earlyBirdStartTime = LocalTime.of(22, 0);
    private final LocalTime earlyBirdFinishTime = LocalTime.of(7, 0);
    private final LocalTime nightOwlStartTime = LocalTime.of(23, 0);
    private final LocalTime nightOwlFinishTime = LocalTime.of(9, 0);

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

        if (Collections.frequency(types, BirdType.EARLY_BIRD)
                == Collections.frequency(types, BirdType.NIGHT_OWL)) {
            return BirdType.DOVE;
        }

        return types.stream()
                .max(Comparator.comparingInt(n -> Collections.frequency(types, n)))
                .orElse(BirdType.DOVE);
    }

    private BirdType countBirdType(SleepSession s) {
        if (s.getStartTime().isBefore(earlyBirdStartTime) &&
                s.getFinishTime().isBefore(earlyBirdFinishTime)) {
            return BirdType.EARLY_BIRD;
        } else if ((s.getStartTime().isAfter(nightOwlStartTime) || s.getStartTime().isBefore(nightOwlFinishTime))
                && s.getFinishTime().isAfter(nightOwlFinishTime)) {
            return BirdType.NIGHT_OWL;
        }
        return BirdType.DOVE;
    }


}


