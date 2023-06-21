package ru.practicum.stats_model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class EndpointHit {
    @NotBlank
    private String app;

    @NotBlank
    private String uri;

    @NotBlank
    private String ip;

    @NotBlank
    private String timestamp;
}