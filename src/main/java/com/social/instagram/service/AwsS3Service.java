package com.social.instagram.service;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {

    void upload(MultipartFile file);

}
