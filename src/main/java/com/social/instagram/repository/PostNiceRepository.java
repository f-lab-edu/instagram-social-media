package com.social.instagram.repository;

import com.social.instagram.domain.PostNice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostNiceRepository extends JpaRepository<PostNice, Long> {

    PostNice findByPostId(long postId);

    @Modifying
    @Transactional
    @Query("UPDATE PostNice SET nice = nice + 1 WHERE postId = :id")
    void updateNice(long id);

}
