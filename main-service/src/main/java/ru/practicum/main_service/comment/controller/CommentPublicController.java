package ru.practicum.main_service.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.main_service.comment.dto.CommentDto;
import ru.practicum.main_service.comment.service.CommentService;
import ru.practicum.main_service.utilities.Constants;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Validated
@Slf4j
public class CommentPublicController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getCommentsByPublic(
            @RequestParam Long eventId,
            @RequestParam(defaultValue = Constants.DEFAULT_FROM) @PositiveOrZero Integer from,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) @Positive Integer size) {
        log.info("GET CommentPublicController getCommentsByPublic eventId = {}", eventId);
        return commentService.getCommentsByPublic(eventId, from, size);
    }

    @GetMapping("/{commentId}")
    public CommentDto getCommentByPublic(@PathVariable Long commentId) {
        log.info("GET CommentPublicController getCommentByPublic commentId = {}", commentId);
        return commentService.getCommentByPublic(commentId);
    }
}