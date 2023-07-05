package ru.practicum.main_service.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.main_service.event.dto.ParticipationRequestDto;
import ru.practicum.main_service.event.service.RequestService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
@Slf4j
public class RequestPrivateController {
    private final RequestService requestService;

    @GetMapping
    public List<ParticipationRequestDto> getEventRequestsByRequester(@PathVariable Long userId) {
        log.info("RequestPrivateController getEventRequestsByRequester userId={}", userId);
        return requestService.getEventRequestsByRequester(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createEventRequest(@PathVariable Long userId,
                                                      @RequestParam Long eventId) {
        log.info("RequestPrivateController createEventRequest userId={}, eventId={}", userId, eventId);
        return requestService.createEventRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelEventRequest(@PathVariable Long userId,
                                                      @PathVariable Long requestId) {
        log.info("RequestPrivateController cancelEventRequest userId={}, requestId={}", userId, requestId);
        return requestService.cancelEventRequest(userId, requestId);
    }
}