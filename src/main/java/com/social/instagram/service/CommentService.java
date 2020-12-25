package com.social.instagram.service;

import com.social.instagram.domain.Comment;
import com.social.instagram.dto.request.CommentRequestDto;
import com.social.instagram.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final SessionService sessionService;

    public void comment(CommentRequestDto commentRequestDto) {
        CommentRequestDto.validate(commentRequestDto, sessionService.getUserId());

        commentRepository.save(Comment.from(commentRequestDto));
    }

}