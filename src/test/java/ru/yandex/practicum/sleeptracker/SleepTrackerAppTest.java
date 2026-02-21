import main.java.ru.yandex.practicum.sleeptracker.DataLoader;
import main.java.ru.yandex.practicum.sleeptracker.FunctionsCollector;
import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;
import main.java.ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.functions.CountGoodSessions;
import main.java.ru.yandex.practicum.sleeptracker.functions.GetBirdType;
import main.java.ru.yandex.practicum.sleeptracker.functions.GetMaxSessionTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {

    private static DataLoader dataLoader;
    private static FunctionsCollector functionsCollector;
    private static List<Function<List<SleepSession>, SleepAnalysisResult>> functions;

    @BeforeAll
    static void setUp() {
        dataLoader = new DataLoader();
        functionsCollector = new FunctionsCollector();
        functions = functionsCollector.getFunctions();
    }

    @Test
    void shouldCreateDataLoader() {
        assertNotNull(dataLoader);
    }

    @Test
    void shouldCreateFunctionsCollector() {
        assertNotNull(functionsCollector);
    }

    @Test
    void shouldCreateFunctionsList() {
        assertNotNull(functions);
    }

    @Test
    void shouldCreateSleepSession() {
        LocalDateTime date = LocalDateTime.of(2026, 2, 21, 3, 3);
        SleepSession sleepSession = new SleepSession(date, date.plusHours(4), SleepQuality.GOOD);
        assertNotNull(sleepSession);
    }

    @Test
    @DisplayName("Должен правильно подсчитывать количество сессий определенного типа")
    void shouldCountGoodSessions() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 21, 3, 3);
        LocalDateTime finish = start.plusHours(5);
        List<SleepSession> sleepSessions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            sleepSessions.add(createSleepSession(start.plusHours(i), finish.plusHours(i)));
        }
        CountGoodSessions countGoodSessions = new CountGoodSessions();
        Object result = countGoodSessions.apply(sleepSessions).getValue();
        assertEquals(5, (long) result);
    }

    @Test
    @DisplayName("При равном количестве жаворонков и сов должен выдавать голубя")
    void shouldReturnDoveIfEqualSessions() {
        LocalDateTime earlyStartTime = LocalDateTime.of(2026, 2, 12, 20, 0);
        LocalDateTime earlyFinishTime = LocalDateTime.of(2026, 2, 13, 4, 0);
        LocalDateTime lateStartTime = LocalDateTime.of(2026, 2, 13, 1, 0);
        LocalDateTime lateFinishTime = LocalDateTime.of(2026, 2, 13, 10, 0);

        SleepSession earlyS1 = createSleepSession(earlyStartTime, earlyFinishTime);
        SleepSession earlyS2 = createSleepSession(earlyStartTime.plusHours(1), earlyFinishTime.plusHours(1));
        SleepSession lateS1 = createSleepSession(lateStartTime, lateFinishTime);
        SleepSession lateS2 = createSleepSession(lateStartTime.plusHours(2), lateFinishTime.plusHours(1));

        List<SleepSession> sleepSessions = new ArrayList<>();
        sleepSessions.add(earlyS1);
        sleepSessions.add(earlyS2);
        sleepSessions.add(lateS1);
        sleepSessions.add(lateS2);

        GetBirdType getBirdType = new GetBirdType();
        Object result = getBirdType.apply(sleepSessions).getValue();
        assertEquals("Голубь", result);
    }

    @Test
    void getMaxSessionTimeCorrectly() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 21, 3, 3);
        LocalDateTime finish = start.plusHours(5);
        List<SleepSession> sleepSessions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            sleepSessions.add(createSleepSession(start, finish.plusHours(i)));
        }
        GetMaxSessionTime getMaxSessionTime = new GetMaxSessionTime();
        Object result = getMaxSessionTime.apply(sleepSessions).getValue();
        assertEquals(Duration.between(start, finish.plusHours(5)).toMinutes(), (long) result);
    }

    private SleepSession createSleepSession(LocalDateTime start, LocalDateTime finish) {
        return new SleepSession(start, finish, SleepQuality.GOOD);
    }

}