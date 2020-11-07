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

    public String upload(MultipartFile file, String userId){
        String bucketName = bucket + userId;
        String fileName = file.getOriginalFilename();

        try(InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, getContentTypeAndLength(file))
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new AwsS3FileNotUploadException();
        }

        log.debug("file name : '{}' upload success!", fileName);
        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    private ObjectMetadata getContentTypeAndLength(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }

}