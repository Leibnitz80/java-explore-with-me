package ru.practicum.main_service.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventRequestStatusUpdateRequest;
import ru.practicum.main_service.event.dto.EventRequestStatusUpdateResult;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.event.dto.ParticipationRequestDto;
import ru.practicum.main_service.event.dto.UpdateEventUserRequest;
import ru.practicum.main_service.event.service.EventService;
import ru.practicum.main_service.event.service.RequestService;
import ru.practicum.main_service.utilities.Constants;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
@Slf4j
@Validated
public class EventPrivateController {
    private final EventService eventService;
    private final RequestService requestService;

    @GetMapping
    public List<EventShortDto> getAllEventsByPrivate(
            @PathVariable Long userId,
            @RequestParam(defaultValue = Constants.DEFAULT_FROM) @PositiveOrZero Integer from,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) @Positive Integer size) {
        log.info("EventPrivateController getAllEventsByPrivate userId={}", userId);
        return eventService.getAllEventsByPrivate(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventByPrivate(
            @PathVariable Long userId,
            @PathVariable Long eventId) {
        log.info("EventPrivateController getEventByPrivate userId={}, eventId={}", userId, eventId);
        return eventService.getEventByPrivate(userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getEventRequestsByEventOwner(
            @PathVariable Long userId,
            @PathVariable Long eventId) {
        log.info("EventPrivateController getEventRequestsByEventOwner userId={}, eventId={}", userId, eventId);
        return requestService.getEventRequestsByEventOwner(userId, eventId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto createEventByPrivate(@PathVariable Long userId,
                                             @Valid @RequestBody NewEventDto newEventDto) {
        log.info("EventPrivateController createEventByPrivate userId={}", userId);
        return eventService.createEventByPrivate(userId, newEventDto);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto patchEventByPrivate(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @Valid @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        log.info("EventPrivateController patchEventByPrivate userId={}, eventId={}", userId, eventId);
        return eventService.patchEventByPrivate(userId, eventId, updateEventUserRequest);
    }

    @PatchMapping("/{eventId}/requests")
    public EventRequestStatusUpdateResult patchEventRequestsByEventOwner(
            @PathVariable Long userId,
            @PathVariable Long eventId,
            @Valid @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        log.info("EventPrivateController patchEventRequestsByEventOwner userId={}, eventId={}", userId, eventId);
        return requestService.patchEventRequestsByEventOwner(userId, eventId, eventRequestStatusUpdateRequest);
    }
}