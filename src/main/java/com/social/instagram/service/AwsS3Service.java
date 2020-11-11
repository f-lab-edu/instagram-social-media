package com.social.instagram.service;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {

    String upload(MultipartFile file, String userId);

}
