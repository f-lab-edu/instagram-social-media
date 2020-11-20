package com.social.instagram.repository;

import com.social.instagram.domain.PostNice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostNiceRepository extends JpaRepository<PostNice, Long> {

    PostNice findByPostId(long postId);

}
