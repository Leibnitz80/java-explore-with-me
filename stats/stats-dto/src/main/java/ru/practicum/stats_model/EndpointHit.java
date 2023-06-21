package ru.practicum.stats_model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@Data
public class EndpointHit {
    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    @NotBlank
    private String app;

    @NotBlank
    private String uri;

    @NotBlank
    private String ip;

    @NotBlank
    @DateTimeFormat(pattern = DATE_TIME_FORMATTER)
    private LocalDateTime timestamp;
}