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
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long postId;

    private String userId;

    private String comment;

    private long nice;

    @Builder
    public Reply(long postId, String userId, String comment, long nice) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.nice = nice;
    }

}