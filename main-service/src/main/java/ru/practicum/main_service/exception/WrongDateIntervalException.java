package ru.practicum.main_service.exception;

public class WrongDateIntervalException extends RuntimeException {
    public WrongDateIntervalException(String message) {
        super(message);
    }
}

