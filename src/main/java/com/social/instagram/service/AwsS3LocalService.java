package com.social.instagram.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.social.instagram.exception.AwsS3FileNotUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class AwsS3LocalService implements AwsS3Service {

    private final AmazonS3 amazonS3Client;
    private final String bucket;

    public void upload(MultipartFile file){
        try(InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, file.getOriginalFilename(), inputStream, getContentTypeAndLength(file))
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new AwsS3FileNotUploadException();
        }

        log.info("file name : '{}' upload success!", file.getOriginalFilename());
    }

    private ObjectMetadata getContentTypeAndLength(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }

}