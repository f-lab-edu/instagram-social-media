package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
import com.social.instagram.domain.Post;
import com.social.instagram.service.AwsS3Service;
import com.social.instagram.service.PostService;
import com.social.instagram.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.social.instagram.util.httpstatus.ResponseConstants.RESPONSE_ENTITY_CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class FileController {

    private final AwsS3Service awsS3Service;
    private final SessionService sessionService;
    private final PostService postService;

    @PostMapping
    @LoginValidation
    public ResponseEntity<Void> upload(@RequestBody MultipartFile file){
        String userId = sessionService.getUserId();
        String filePath = awsS3Service.upload(file, userId);

        postService.writePost(Post.changePostEntity(filePath, userId));

        return RESPONSE_ENTITY_CREATE;
    }

}