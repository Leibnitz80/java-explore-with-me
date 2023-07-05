package ru.practicum.main_service.event.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.category.mapper.CategoryMapper;
import ru.practicum.main_service.category.model.Category;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.event.enums.EventState;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.model.Location;
import ru.practicum.main_service.user.mapper.UserMapper;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;

@UtilityClass
public class EventMapper {
    public EventFullDto toEventFullDto(Event event, Long confirmedRequests, Long views) {
        final EventFullDto result = new EventFullDto();
        result.setId(event.getId());
        result.setAnnotation(event.getAnnotation());
        result.setCategory(CategoryMapper.toCategoryDto(event.getCategory()));
        result.setConfirmedRequests(confirmedRequests);
        result.setCreatedOn(event.getCreatedOn());
        result.setDescription(event.getDescription());
        result.setEventDate(event.getEventDate());
        result.setInitiator(UserMapper.toUserShortDto(event.getInitiator()));
        result.setLocation(LocationMapper.toLocationDto(event.getLocation()));
        result.setPaid(event.getPaid());
        result.setParticipantLimit(event.getParticipantLimit());
        result.setPublishedOn(event.getPublishedOn());
        result.setRequestModeration(event.getRequestModeration());
        result.setState(event.getState());
        result.setTitle(event.getTitle());
        result.setViews(views);
        result.setTitle(event.getTitle());
        return result;
    }

    public Event toEvent(NewEventDto newEventDto, User initiator, Category category, Location location, LocalDateTime createdOn,
                         EventState state) {
        final Event result = new Event();
        result.setTitle(newEventDto.getTitle());
        result.setAnnotation(newEventDto.getAnnotation());
        result.setCategory(category);
        result.setDescription(newEventDto.getDescription());
        result.setPaid(newEventDto.getPaid());
        result.setParticipantLimit(newEventDto.getParticipantLimit());
        result.setEventDate(newEventDto.getEventDate());
        result.setLocation(location);
        result.setCreatedOn(createdOn);
        result.setState(state);
        result.setInitiator(initiator);
        result.setRequestModeration(newEventDto.getRequestModeration());
        return result;
    }

    public EventShortDto toEventShortDto(Event event, Long confirmedRequests, Long views) {
        final EventShortDto result = new EventShortDto();
        result.setId(event.getId());
        result.setAnnotation(event.getAnnotation());
        result.setCategory(CategoryMapper.toCategoryDto(event.getCategory()));
        result.setConfirmedRequests(confirmedRequests);
        result.setEventDate(event.getEventDate());
        result.setInitiator(UserMapper.toUserShortDto(event.getInitiator()));
        result.setPaid(event.getPaid());
        result.setTitle(event.getTitle());
        result.setViews(views);
        return result;
    }
}