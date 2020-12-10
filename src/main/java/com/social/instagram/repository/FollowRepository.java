package com.social.instagram.repository;

import com.social.instagram.domain.Follow;
import com.social.instagram.exception.FollowOperationFailedException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Transactional(rollbackFor = FollowOperationFailedException.class)
    void deleteByUserIdAndFollowId(String userId, String followId);

}