package com.social.instagram.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.social.instagram.exception.AwsS3FileNotUploadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AwsS3LocalServiceTest {

    @Mock
    private AmazonS3 amazonS3Client;

    @InjectMocks
    private AwsS3LocalService awsS3LocalService;

    private MultipartFile file;

    private static final String NAME = "picture";
    private static final String ORIGINAL_FILE_NAME = "picture.jpeg";
    private static final String CONTENT_TYPE = "image/jpeg";
    private static final byte[] CONTENT_STREAM = ORIGINAL_FILE_NAME.getBytes();
    private static final String USER_ID = "testId";
    private static final String AWS_URL =
            "https://s3.ap-northeast-2.amazonaws.com/instagram-image-post/testId/picture.jpeg";

    @BeforeEach
    public void setUp() {
        this.file = new MockMultipartFile(NAME, ORIGINAL_FILE_NAME, CONTENT_TYPE, CONTENT_STREAM);
    }

    @Test
    @DisplayName("사진을 S3에 저장하고 해당 사진 URL을 반환한다")
    public void getPictureUrlAfterS3Save() throws MalformedURLException {
        URL awsS3Url = new URL(AWS_URL);
        given(amazonS3Client.putObject(any())).willReturn(mock(PutObjectResult.class));
        given(amazonS3Client.getUrl(anyString(), anyString())).willReturn(awsS3Url);

        String resultUrl = awsS3LocalService.upload(file, USER_ID);

        verify(amazonS3Client).putObject(any());
        assertThat(resultUrl, equalTo(awsS3Url.toString()));
    }

    @Test
    @DisplayName("S3에 장애가 나면 AmazonServiceException이 발생하고 AwsS3FileNotUploadException를 던진다")
    public void throwAwsS3FileNotUploadExceptionWhenS3Barrier() {
        given(amazonS3Client.putObject(any())).willThrow(AmazonServiceException.class);

        assertThrows(AwsS3FileNotUploadException.class,
                () -> awsS3LocalService.upload(file, USER_ID));
    }

}