package com.social.instagram.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
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
    private static final String ORIGINAL_FILE_NAME = "picture";
    private static final String CONTENT_TYPE = "image/jpeg";
    private static final String USER_ID = "testId";

    @BeforeEach
    public void setUp() {
        //todo MockMultipartFile에 맨끝에 contentStream 넣기
        this.file = new MockMultipartFile(NAME, ORIGINAL_FILE_NAME, CONTENT_TYPE);
    }

    @Test
    @DisplayName("사진을 S3에 저장하고 해당 사진 URL을 반환한다")
    public void getPictureUrlAfterS3Save() {
        given(amazonS3Client.putObject(any())).willReturn(mock(PutObjectResult.class));

        awsS3LocalService.upload(file, USER_ID);

        //todo url값 검증 하기 assertThat();
        verify(amazonS3Client).putObject(any());
    }

}