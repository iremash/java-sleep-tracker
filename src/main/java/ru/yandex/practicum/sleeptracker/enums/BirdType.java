package main.java.ru.yandex.practicum.sleeptracker.enums;

public enum BirdType {
    DOVE,
    NIGHT_OWL,
    EARLY_BIRD;

    public static String toString(BirdType type) {
        return switch (type) {
            case DOVE -> "Голубь";
            case EARLY_BIRD -> "Жаворонок";
            case NIGHT_OWL -> "Сова";
        };
    }
}