package ru.practicum.main_service.comment.service;

import ru.practicum.main_service.comment.dto.CommentDto;
import ru.practicum.main_service.comment.dto.NewCommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByAdmin(Integer from, Integer size);

    void deleteByAdmin(Long commentId);

    List<CommentDto> getCommentsByPrivate(Long userId, Long eventId, Integer from, Integer size);

    CommentDto createByPrivate(Long userId, Long eventId, NewCommentDto newCommentDto);

    CommentDto patchByPrivate(Long userId, Long commentId, NewCommentDto newCommentDto);

    void deleteByPrivate(Long userId, Long commentId);

    List<CommentDto> getCommentsByPublic(Long eventId, Integer from, Integer size);

    CommentDto getCommentByPublic(Long commentId);
}