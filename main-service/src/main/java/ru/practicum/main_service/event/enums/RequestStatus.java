package ru.practicum.main_service.event.enums;

public enum RequestStatus {
    CANCELED("Отменён"),
    PENDING("Ожидает"),
    CONFIRMED("Подтверждён"),
    REJECTED("Отклонён");

    private final String description;

    RequestStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
