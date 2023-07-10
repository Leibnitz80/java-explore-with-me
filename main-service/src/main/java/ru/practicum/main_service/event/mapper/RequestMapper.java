package ru.practicum.main_service.event.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.event.dto.ParticipationRequestDto;
import ru.practicum.main_service.event.model.Request;

@UtilityClass
public class RequestMapper {
    public ParticipationRequestDto toParticipationRequestDto(Request request) {
        ParticipationRequestDto result = new ParticipationRequestDto();
        result.setId(request.getId());
        result.setEvent(request.getEvent().getId());
        result.setRequester(request.getRequester().getId());
        result.setCreated(request.getCreated());
        result.setStatus(request.getStatus());
        return result;
    }
}