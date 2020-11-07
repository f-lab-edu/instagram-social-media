package com.social.instagram.domain;

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
public class PostNiceClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long postId;

    private String niceClickUserId;

    @Builder
    public PostNiceClick(long postId, String niceClickUserId) {
        this.postId = postId;
        this.niceClickUserId = niceClickUserId;
    }

}