package com.social.instagram.domain;

import com.social.instagram.domain.time.BaseTimeEntity;
import com.social.instagram.dto.request.CommentRequestDto;
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
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long postId;

    private String userId;

    private String comment;

    private long nice;

    private boolean reply;

    @Builder
    public Comment(long postId, String userId, String comment, long nice, boolean reply) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.nice = nice;
        this.reply = reply;
    }

    public static Comment from(CommentRequestDto commentRequestDto, String userId) {
        return Comment.builder()
                .postId(commentRequestDto.getPostId())
                .userId(userId)
                .comment(commentRequestDto.getComment())
                .build();
    }

}