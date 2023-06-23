package ru.practicum.stats_server.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.stats_model.EndpointHit;
import ru.practicum.stats_server.model.Stats;

import java.time.LocalDateTime;

@UtilityClass
public class StatsMapper {
    public static Stats endpointToStats(EndpointHit endpointHit, LocalDateTime timestamp) {
        Stats stats = new Stats();
        stats.setApp(endpointHit.getApp());
        stats.setTimestamp(timestamp);
        stats.setUri(endpointHit.getUri());
        stats.setIp(endpointHit.getIp());
        return stats;
    }
}