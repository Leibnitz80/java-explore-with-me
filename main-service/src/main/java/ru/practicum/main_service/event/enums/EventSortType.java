package ru.practicum.main_service.event.enums;

public enum EventSortType {
    EVENT_DATE("Сортировка по дате события"),
    VIEWS("Сортировка количеству просмотров");

    private final String description;

    EventSortType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}