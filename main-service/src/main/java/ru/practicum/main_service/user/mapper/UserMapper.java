package ru.practicum.main_service.user.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.user.dto.NewUserRequest;
import ru.practicum.main_service.user.dto.UserDto;
import ru.practicum.main_service.user.dto.UserShortDto;
import ru.practicum.main_service.user.model.User;

@UtilityClass
public class UserMapper {
    public static User toUser(NewUserRequest newUserRequest) {
        User result = new User();
        result.setName(newUserRequest.getName());
        result.setEmail(newUserRequest.getEmail());
        return result;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getEmail(), user.getId(), user.getName());
    }

    public static UserShortDto toUserShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }
}