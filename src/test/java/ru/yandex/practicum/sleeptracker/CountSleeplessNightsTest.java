import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;
import main.java.ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.functions.CountSleeplessNights;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountSleeplessNightsTest {

    private CountSleeplessNights countSleeplessNights;

    @BeforeEach
    void setUp() {
        countSleeplessNights = new CountSleeplessNights();
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        List<SleepSession> emptyList = Collections.emptyList();
        SleepAnalysisResult result = countSleeplessNights.apply(emptyList);
        assertEquals(0L, result.getValue());
    }

    @Test
    @DisplayName(
            "Метод должен засчитывать ночь бессонной, если она началась после 6 утра и закончилась в тот же день.")
    void shouldCountSleeplessNightWhenStartAfter6AndFinishSameDay() {
        LocalDateTime date = LocalDateTime.of(2026, 2, 21, 8, 0);
        SleepSession session = createSleepSession(date, date.plusHours(2));
        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session));
        assertEquals(1L, result.getValue());
    }

    @Test
    @DisplayName("Если ночь началась до 6 утра, она должна не считаться бессонной.")
    void shouldNotCountSleeplessNightWhenStartBefore6() {
        LocalDateTime date = LocalDateTime.of(2026, 2, 21, 5, 0);
        SleepSession session = createSleepSession(date, date.plusHours(3));
        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session));
        assertEquals(0L, result.getValue());
    }


    @Test
    @DisplayName("Если ночь началась ровно в 6 утра, она должна считаться бессонной.")
    void shouldCountSleeplessNightWhenStartAt600() {

        LocalDateTime date = LocalDateTime.of(2026, 2, 21, 6, 0);
        SleepSession session = createSleepSession(date, date.plusHours(2));


        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session));

        assertEquals(1L, result.getValue());
    }


    @Test
    @DisplayName("Должен корректно обрабатывать несколько сессий сна за один день")
    void shouldCountCorrectlyMultipleSessionsForSameDay() {
        LocalDateTime date = LocalDateTime.of(2026, 2, 21, 8, 0);

        SleepSession sleeplessSession1 = createSleepSession(date, date.plusHours(2));
        SleepSession sleeplessSession2 = createSleepSession(date.plusHours(3), date.plusHours(3));
        SleepSession normalSession = createSleepSession(date.withHour(5), date.withHour(7));

        List<SleepSession> sessions = Arrays.asList(sleeplessSession1, sleeplessSession2, normalSession);

        SleepAnalysisResult result = countSleeplessNights.apply(sessions);

        assertEquals(1L, result.getValue());
    }

    @Test
    @DisplayName("Должен правильно подсчитывать сессии для разных дней")
    void shouldCountSessionsForDifferentDays() {
        LocalDateTime day1 = LocalDateTime.of(2026, 2, 21, 8, 0);
        LocalDateTime day2 = LocalDateTime.of(2026, 2, 22, 9, 0);
        LocalDateTime day3 = LocalDateTime.of(2026, 2, 23, 7, 0);

        SleepSession session1 = createSleepSession(day1, day1.plusHours(2));
        SleepSession session2 = createSleepSession(day2, day2.plusHours(1));
        SleepSession session3 = createSleepSession(day3, day3.plusHours(4));

        List<SleepSession> sessions = Arrays.asList(session1, session2, session3);

        SleepAnalysisResult result = countSleeplessNights.apply(sessions);

        assertEquals(3L, result.getValue());
    }


    @Test
    @DisplayName("Должен правильно распределять сессию, начавшуюся в 00:01")
    void shouldHandleSessionStartedAt001() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 21, 0, 1);
        LocalDateTime finish = LocalDateTime.of(2026, 2, 21, 3, 0);
        SleepSession session = createSleepSession(start, finish);

        SleepAnalysisResult result = countSleeplessNights.apply(List.of(session));

        assertEquals(0L, result.getValue());
    }

    private SleepSession createSleepSession(LocalDateTime start, LocalDateTime finish) {
        return new SleepSession(start, finish, SleepQuality.GOOD);
    }
}