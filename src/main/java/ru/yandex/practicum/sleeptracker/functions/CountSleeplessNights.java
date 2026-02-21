package main.java.ru.yandex.practicum.sleeptracker.functions;

import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights implements Function<List<SleepSession>, SleepAnalysisResult> {
    final LocalTime DAY_BOARDER = LocalTime.of(12, 0);
    final LocalTime NIGHT_END = LocalTime.of(5, 59);


    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        return formMessage(countSleeplessNights(sleepSessions));
    }

    private Long countSleeplessNights(List<SleepSession> sleepSessions) {
        HashMap<LocalDateTime, Boolean> daysData = new HashMap<>();
        sleepSessions
                .forEach(s -> {
                    if (s.getStartTime().isBefore(DAY_BOARDER)) {
                        daysData.merge(s.getStartDate(), checkIfSleepless(s), (ov, nv) -> ov && nv);
                    } else {
                        daysData.merge(getDayAfter(s.getStartDate()), checkIfSleepless(s), (oldValue, newValue) -> oldValue && newValue);
                    }
                });
        return daysData.values().stream()
                .filter(b -> b)
                .count();
    }

    private boolean checkIfSleepless(SleepSession s1) {
        return (s1.getStartTime().isBefore(s1.getFinishTime()) && s1.getStartTime().isAfter(NIGHT_END));
    }

    private LocalDateTime getDayAfter(LocalDateTime d) {
        return d.plusDays(1);
    }

    private SleepAnalysisResult formMessage(Long amount) {
        return new SleepAnalysisResult("Количество бессоных ночей", amount);
    }
}
