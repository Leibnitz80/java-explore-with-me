package ru.practicum.stats_server.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.stats_model.EndpointHit;
import ru.practicum.stats_server.model.Stats;

import java.time.LocalDateTime;

@UtilityClass
public class StatsMapper {
    public static Stats endpointToStats(EndpointHit endpointHit, LocalDateTime timestamp) {
        return new Stats(null, endpointHit.getApp(), endpointHit.getUri(), endpointHit.getIp(), timestamp);
    }
}