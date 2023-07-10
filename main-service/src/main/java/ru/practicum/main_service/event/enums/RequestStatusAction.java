package ru.practicum.main_service.event.enums;

public enum RequestStatusAction {
    CONFIRMED("Подтвердить"),
    REJECTED("Отклонить");

    private String description;

    RequestStatusAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}