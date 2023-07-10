package ru.practicum.main_service.utilities;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class Constants {
    public final String DEFAULT_FROM = "0";
    public final String DEFAULT_SIZE = "10";
    public final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
