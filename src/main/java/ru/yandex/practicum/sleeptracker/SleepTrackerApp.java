package main.java.ru.yandex.practicum.sleeptracker;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        FunctionsCollector functionsCollector = new FunctionsCollector();
        List<Function<List<SleepSession>, SleepAnalysisResult>> functions = functionsCollector.getFunctions();

        try {
            final List<SleepSession> allSleepSessions = dataLoader.loadData(
                    args[0]);
            functions
                    .forEach(func -> System.out.println(func.apply(allSleepSessions)));
        } catch (IOException e) {
            System.err.println("Ошибка с загрузкой файла.");
        }

    }
}