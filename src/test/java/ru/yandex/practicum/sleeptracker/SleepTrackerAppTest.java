import main.java.ru.yandex.practicum.sleeptracker.DataLoader;
import main.java.ru.yandex.practicum.sleeptracker.FunctionsCollector;
import main.java.ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import main.java.ru.yandex.practicum.sleeptracker.SleepSession;
import main.java.ru.yandex.practicum.sleeptracker.enums.SleepQuality;
import main.java.ru.yandex.practicum.sleeptracker.functions.CountGoodSessions;
import main.java.ru.yandex.practicum.sleeptracker.functions.GetMaxSessionTime;
import org.junit.jupiter.api.BeforeAll;
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
    void shouldCountGOODSessions() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 21, 3, 3);
        LocalDateTime finish = start.plusHours(5);
        List<SleepSession> sleepSessions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            sleepSessions.add(createSleepSession(start.plusHours(i), finish.plusHours(i)));
        }
        CountGoodSessions countGoodSessions = new CountGoodSessions();
        Object result = countGoodSessions.apply(sleepSessions).getValue();
        assertTrue(5 == (long) result);
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