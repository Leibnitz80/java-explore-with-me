package ru.practicum.stats_server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats_server.service.StatsService;
import ru.practicum.stats_model.EndpointHit;
import ru.practicum.stats_model.ViewStats;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {
    private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private final StatsService statsService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@Valid @RequestBody EndpointHit endpointHit) {
        log.info("POST addHit uri:{}, ip:{}", endpointHit.getUri(), endpointHit.getIp());
        statsService.addHit(endpointHit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getStats(@RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMATTER) LocalDateTime start,
                                    @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMATTER) LocalDateTime end,
                                    @RequestParam(required = false) List<String> uris,
                                    @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("GET getStats start:{}, end:{}", start, end);
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Wrong date interval!");
        }
        return statsService.getStats(start, end, uris, unique);
    }
}