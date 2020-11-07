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
public class ReplyNiceClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long post_id;

    private String userId;

    private String niceClickUserId;

    @Builder
    public ReplyNiceClick(long post_id, String userId, String niceClickUserId) {
        this.post_id = post_id;
        this.userId = userId;
        this.niceClickUserId = niceClickUserId;
    }

}