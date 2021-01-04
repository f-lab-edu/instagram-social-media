package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
import com.social.instagram.domain.Post;
import com.social.instagram.service.AwsS3Service;
import com.social.instagram.service.PostService;
import com.social.instagram.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class FileController {

    private final AwsS3Service awsS3Service;
    private final SessionService sessionService;
    private final PostService postService;

    @PostMapping
    @LoginValidation
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody MultipartFile file) {
        String userId = sessionService.getUserId();
        String filePath = awsS3Service.upload(file, userId);

        postService.writePost(Post.changePostEntity(filePath, userId));
    }

}