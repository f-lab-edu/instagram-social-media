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
    private final LoginService loginService;

    public void comment(CommentRequestDto commentRequestDto) {
        commentRepository.save(Comment.from(commentRequestDto, loginService.getUserId()));
    }

}