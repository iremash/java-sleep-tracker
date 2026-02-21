package main.java.ru.yandex.practicum.sleeptracker;

import main.java.ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataLoader {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepSession> loadData(String filename) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream
                    .map(this::parseLine)
                    .collect(Collectors.toList());

        }
    }

    private SleepSession parseLine(String line) {
        String[] lineParts = line.split(";");
        return new SleepSession(parseTime(lineParts[0]), parseTime(lineParts[1]),
                SleepQuality.valueOf(lineParts[2]));
    }

    private LocalDateTime parseTime(String time) {
        return LocalDateTime.parse(time, dateTimeFormatter);
    }

}
