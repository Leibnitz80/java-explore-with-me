package ru.practicum.main_service.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.main_service.comment.dto.NewCommentDto;
import ru.practicum.main_service.comment.dto.CommentDto;
import ru.practicum.main_service.comment.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/comments")
@Validated
@Slf4j
public class CommentPrivateController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getCommentsByPrivate(
            @PathVariable Long userId,
            @RequestParam(required = false) Long eventId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("GET CommentPrivateController getCommentsByPrivate userId = {}, eventId = {}", userId, eventId);
        return commentService.getCommentsByPrivate(userId, eventId, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createByPrivate(@PathVariable Long userId,
                                      @RequestParam Long eventId,
                                      @Valid @RequestBody NewCommentDto newCommentDto) {
        log.info("POST CommentPrivateController createByPrivate userId = {}, eventId = {}", userId, eventId);
        return commentService.createByPrivate(userId, eventId, newCommentDto);
    }

    @PatchMapping("/{commentId}")
    public CommentDto patchByPrivate(@PathVariable Long userId,
                                     @PathVariable Long commentId,
                                     @Valid @RequestBody NewCommentDto newCommentDto) {
        log.info("PATCH CommentPrivateController patchByPrivate userId = {}, commentId = {}", userId, commentId);
        return commentService.patchByPrivate(userId, commentId, newCommentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByPrivate(@PathVariable Long userId,
                                @PathVariable Long commentId) {
        log.info("DELETE CommentPrivateController deleteByPrivate userId = {}, commentId = {}", userId, commentId);
        commentService.deleteByPrivate(userId, commentId);
    }
}