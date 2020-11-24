package com.social.instagram.dto.response;

import com.social.instagram.domain.Post;
import com.social.instagram.domain.PostNice;
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

    private long nice;

    @Builder
    public PostResponseDto(long id, String userId, String comment,
                           LocalDateTime createdTime, LocalDateTime modifiedTime,
                           String filePath, long nice) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.filePath = filePath;
        this.nice = nice;
    }

    public static PostResponseDto from(Post post, PostNice postNice) {
        return PostResponseDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .comment(post.getComment())
                .createdTime(post.getCreatedTime())
                .modifiedTime(post.getModifiedTime())
                .filePath(post.getFilePath())
                .nice(postNice.getNice())
                .build();
    }

}