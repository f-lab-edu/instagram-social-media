package com.social.instagram.repository;

import com.social.instagram.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true, value = "SELECT id FROM Post WHERE user_id = :userId ORDER BY id DESC LIMIT 1")
    int findByPostId(String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Post SET comment = :comment WHERE id = :postId")
    void updateComment(long postId, String comment);

    List<Post> findByUserIdAndFilePathIsNotNull(String userId);

}