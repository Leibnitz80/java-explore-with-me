package ru.practicum.main_service.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.main_service.comment.dto.CommentDto;
import ru.practicum.main_service.comment.service.CommentService;
import ru.practicum.main_service.utilities.Constants;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
@Validated
@Slf4j
public class CommentAdminController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> getCommentsByAdmin(
            @RequestParam(defaultValue = Constants.DEFAULT_FROM) @PositiveOrZero Integer from,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) @Positive Integer size) {
        log.info("GET CommentAdminController getCommentsByAdmin");
        return commentService.getCommentsByAdmin(from, size);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByAdmin(@PathVariable Long commentId) {
        log.info("DELETE CommentAdminController deleteByAdmin commentId = {}", commentId);
        commentService.deleteByAdmin(commentId);
    }
}