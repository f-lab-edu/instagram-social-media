package com.social.instagram.repository;

import com.social.instagram.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUserId(String userId);
}