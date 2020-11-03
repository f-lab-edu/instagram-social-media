package com.social.instagram.controller;

import com.social.instagram.service.AwsS3LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class FileController {

    private final AwsS3LocalService awsS3LocalService;

    @PostMapping
    public ResponseEntity<Void> upload(@RequestBody MultipartFile file){
        awsS3LocalService.upload(file);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}