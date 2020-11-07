package com.social.instagram.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostNice {

    @Id
    private long postId;

    long nice;

    @Builder
    public PostNice(long postId, long nice) {
        this.postId = postId;
        this.nice = nice;
    }

}