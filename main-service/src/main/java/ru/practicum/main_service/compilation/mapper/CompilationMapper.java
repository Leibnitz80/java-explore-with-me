package ru.practicum.main_service.compilation.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.dto.NewCompilationDto;
import ru.practicum.main_service.compilation.model.Compilation;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.model.Event;

import java.util.List;

@UtilityClass
public class CompilationMapper {
    public static Compilation newDtoToCompilation(NewCompilationDto newCompilationDto, List<Event> events) {
        Compilation result = new Compilation();
        result.setTitle(newCompilationDto.getTitle());
        result.setPinned(newCompilationDto.getPinned());
        result.setEvents(events);
        return result;
    }

    public static CompilationDto toCompilationDto(Compilation compilation, List<EventShortDto> eventsShortDto) {
        CompilationDto result = new CompilationDto();
        result.setId(compilation.getId());
        result.setTitle(compilation.getTitle());
        result.setPinned(compilation.getPinned());
        result.setEvents(eventsShortDto);
        return result;
    }
}