package com.social.instagram.repository;

import com.social.instagram.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true, value = "select id from Post where user_id = :userId order by id desc limit 1")
    int findByPostId(String userId);

    @Modifying
    @Transactional
    @Query("update Post set comment = :comment where id = :postId")
    void updateComment(long postId, String comment);

    List<Post> findByUserIdAndFilePathIsNotNull(String userId);

}