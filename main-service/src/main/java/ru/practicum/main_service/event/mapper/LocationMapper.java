package ru.practicum.main_service.event.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.event.dto.LocationDto;
import ru.practicum.main_service.event.model.Location;

@UtilityClass
public class LocationMapper {
    public static Location toLocation(LocationDto locationDto) {
        final Location result = new Location();
        result.setLat(locationDto.getLat());
        result.setLon(locationDto.getLon());
        return result;
    }

    public static LocationDto toLocationDto(Location location) {
        final LocationDto result = new LocationDto();
        result.setLat(location.getLat());
        result.setLon(location.getLon());
        return result;
    }
}