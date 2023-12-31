package ru.practicum.main_service.user.service;

import ru.practicum.main_service.user.dto.NewUserRequest;
import ru.practicum.main_service.user.dto.UserDto;
import ru.practicum.main_service.user.model.User;

import java.util.List;

public interface UserService {
    UserDto create(NewUserRequest newUserRequest);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteById(Long id);

    User getUserById(Long id);
}