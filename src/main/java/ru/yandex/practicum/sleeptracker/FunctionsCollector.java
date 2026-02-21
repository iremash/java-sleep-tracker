package main.java.ru.yandex.practicum.sleeptracker;


import main.java.ru.yandex.practicum.sleeptracker.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionsCollector {
    List<Function<List<SleepSession>, SleepAnalysisResult>> functions = new ArrayList<>();


    public List<Function<List<SleepSession>, SleepAnalysisResult>> getFunctions() {
        formFunctionsList();
        return functions;
    }

    public void formFunctionsList() {

        functions.add(new GetSessionsAmount());
        functions.add(new GetMinSessionTIme());
        functions.add(new GetMaxSessionTime());
        functions.add(new GetBirdType());
        functions.add(new GetAverageSleep());
        functions.add(new CountSleeplessNights());
        functions.add(new CountNormalSessions());
        functions.add(new CountGoodSessions());
        functions.add(new CountBadSessions());
    }

}
