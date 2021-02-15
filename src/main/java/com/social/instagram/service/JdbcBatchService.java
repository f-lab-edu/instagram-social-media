package com.social.instagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcBatchService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert(final String query, final List<?> batchMessage) {
        namedParameterJdbcTemplate.batchUpdate(query, SqlParameterSourceUtils.createBatch(batchMessage.toArray()));
    }

}