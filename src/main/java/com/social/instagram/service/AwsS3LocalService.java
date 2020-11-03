package com.social.instagram.service;

import com.social.instagram.util.aws.AwsS3Connector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class AwsS3LocalService {

    private final AwsS3Connector awsS3Connector;

    public void upload(MultipartFile file) {
        awsS3Connector.upload(file);
    }

}