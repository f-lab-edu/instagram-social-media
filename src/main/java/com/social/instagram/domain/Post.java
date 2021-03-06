package com.social.instagram.domain;

import com.social.instagram.domain.time.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;

    private String comment;

    private String filePath;

    @Builder
    public Post(String userId, String comment, String filePath) {
        this.userId = userId;
        this.comment = comment;
        this.filePath = filePath;
    }

    public static Post from(String filePath, String userId) {
        return Post.builder()
                .filePath(filePath)
                .userId(userId)
                .build();
    }

}