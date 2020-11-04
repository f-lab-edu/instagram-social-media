package com.social.instagram.controller;

import com.social.instagram.annotation.LoginValidation;
import com.social.instagram.service.AwsS3Service;
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

    @PostMapping
    @LoginValidation
    public ResponseEntity<Void> upload(@RequestBody MultipartFile file){
        awsS3Service.upload(file);

        return RESPONSE_ENTITY_CREATE;
    }

}