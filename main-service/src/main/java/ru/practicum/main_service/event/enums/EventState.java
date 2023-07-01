package ru.practicum.main_service.event.enums;

public enum EventState {
    CANCELED("Отменено"),
    PENDING("Ожидает публикации"),
    PUBLISHED("Опубликовано"),
    REJECTED("Отклонено");

    private final String description;

    EventState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}