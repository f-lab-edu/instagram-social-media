package com.social.instagram.dto.response;

import com.social.instagram.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private long id;

    private String userId;

    private String comment;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    private String filePath;

    @Builder
    public PostResponseDto(long id, String userId, String comment,
                           LocalDateTime createdTime, LocalDateTime modifiedTime,
                           String filePath) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.filePath = filePath;
    }

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .comment(post.getComment())
                .createdTime(post.getCreatedTime())
                .modifiedTime(post.getModifiedTime())
                .filePath(post.getFilePath())
                .build();
    }

}