package ru.practicum.main_service.comment.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.comment.dto.CommentDto;
import ru.practicum.main_service.comment.model.Comment;
import ru.practicum.main_service.user.mapper.UserMapper;

@UtilityClass
public class CommentMapper {
    public CommentDto toCommentDto(Comment comment) {
        final CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setEventId(comment.getEvent().getId());
        commentDto.setAuthor(UserMapper.toUserShortDto(comment.getAuthor()));
        commentDto.setCreatedOn(comment.getCreatedOn());
        commentDto.setEditedOn(comment.getEditedOn());
        return commentDto;
    }
}