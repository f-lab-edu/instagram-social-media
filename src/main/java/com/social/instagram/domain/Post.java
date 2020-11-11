package com.social.instagram.domain;

import com.social.instagram.dto.PostDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;

    private String comment;

    private String filePath;

    @CreationTimestamp
    private LocalDateTime createTime;

    private long nice;

    @Builder
    public Post(String userId, String comment, String filePath) {
        this.userId = userId;
        this.comment = comment;
        this.filePath = filePath;
    }

    public static Post changePostEntity(PostDto postDto, String userId) {
        return Post.builder()
                .userId(userId)
                .comment(postDto.getComment())
                .build();
    }

    public static Post changePostEntity(String filePath, String userId) {
        return Post.builder()
                .filePath(filePath)
                .userId(userId)
                .build();
    }

}