package ru.practicum.main_service.event.enums;

public enum EventStateAction {
    SEND_TO_REVIEW("Отправить на модерацию"),
    CANCEL_REVIEW("Отменить модерацию"),
    PUBLISH_EVENT("Опубликовать"),
    REJECT_EVENT("Отклонить");

    private final String description;

    EventStateAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}