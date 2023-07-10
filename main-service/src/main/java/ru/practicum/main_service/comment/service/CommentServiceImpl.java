package ru.practicum.main_service.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.comment.dto.CommentDto;
import ru.practicum.main_service.comment.dto.NewCommentDto;
import ru.practicum.main_service.comment.mapper.CommentMapper;
import ru.practicum.main_service.comment.model.Comment;
import ru.practicum.main_service.comment.repository.CommentRepository;
import ru.practicum.main_service.event.enums.EventState;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.repository.EventRepository;
import ru.practicum.main_service.exception.ForbiddenException;
import ru.practicum.main_service.exception.NotFoundException;
import ru.practicum.main_service.user.model.User;
import ru.practicum.main_service.user.repository.UserRepository;
import ru.practicum.main_service.utilities.PageRequestExt;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDto> getCommentsByAdmin(Integer from, Integer size) {
        return toCommentsDto(commentRepository.findAll(PageRequestExt.of(from, size)).toList());
    }

    @Override
    @Transactional
    public void deleteByAdmin(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPrivate(Long userId, Long eventId, Integer from, Integer size) {
        userRepository.findById(userId);

        List<Comment> comments;
        if (eventId != null) {
            eventRepository.findById(eventId);

            comments = commentRepository.findAllByAuthorIdAndEventId(userId, eventId);
        } else {
            comments = commentRepository.findAllByAuthorId(userId);
        }

        return toCommentsDto(comments);
    }

    @Override
    @Transactional
    public CommentDto createByPrivate(Long userId, Long eventId, NewCommentDto newCommentDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No such user id " + userId));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("No event with id " + eventId));

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ForbiddenException("Forbidden: event not published yet");
        }

        Comment comment = Comment.builder()
                .text(newCommentDto.getText())
                .author(user)
                .event(event)
                .createdOn(LocalDateTime.now())
                .build();

        return CommentMapper.toCommentDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public CommentDto patchByPrivate(Long userId, Long commentId, NewCommentDto newCommentDto) {
        userRepository.findById(userId);

        Comment commentFromRepository = getCommentById(commentId);

        checkUserIsOwner(userId, commentFromRepository.getAuthor().getId());

        commentFromRepository.setText(newCommentDto.getText());
        commentFromRepository.setEditedOn(LocalDateTime.now());

        return CommentMapper.toCommentDto(commentRepository.save(commentFromRepository));
    }

    @Override
    @Transactional
    public void deleteByPrivate(Long userId, Long commentId) {
        userRepository.findById(userId);

        checkUserIsOwner(userId, getCommentById(commentId).getAuthor().getId());

        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPublic(Long eventId, Integer from, Integer size) {
        eventRepository.findById(eventId);

        return toCommentsDto(commentRepository.findAllByEventId(eventId, PageRequestExt.of(from, size)));
    }

    @Override
    public CommentDto getCommentByPublic(Long commentId) {
        return CommentMapper.toCommentDto(getCommentById(commentId));
    }

    private List<CommentDto> toCommentsDto(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Not found Comment Id: " + commentId));
    }

    private void checkUserIsOwner(Long id, Long userId) {
        if (!Objects.equals(id, userId)) {
            throw new ForbiddenException("Forbidden: user is not an owner");
        }
    }
}