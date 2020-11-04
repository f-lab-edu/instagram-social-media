package com.social.instagram.util.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.social.instagram.exception.AwsS3FileNotUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/*
    AwsS3Connector
    AwsS3Connector class를 만들어 S3에 필요한 기능들만 사용할 수 있게 캡슐화하였고 또한 upload 기능을 사용하기 위해선
    Ioexception을 처리해줘야 하는데 try-with-resources를 사용하여 자원 해제 처리를 자동으로 종료 할 수 있게 구현했다.
*/
@Configuration
@RequiredArgsConstructor
public class AwsS3Connector {

    private final AmazonS3 amazonS3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public void upload(MultipartFile file){
        try(InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, file.getOriginalFilename(), inputStream, getContentTypeAndLength(file))
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new AwsS3FileNotUploadException();
        }
    }

    private ObjectMetadata getContentTypeAndLength(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }

}