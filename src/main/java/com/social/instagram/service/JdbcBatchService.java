package com.social.instagram.service;

import com.social.instagram.dto.request.FeedNiceBatchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcBatchService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*
        NamedParameterJdbcTemplate의 batchUpdate 메소드는 PreparedStatement에서 값을 자동으로 설정하는데
        일반적으로 잘 작동하지만 Map에 포함된 Null값일 경우 문제가 발생할 수 있음

        ParameterMetaData.getParameterType은 위의 동작 방식일 경우 호출하게 되는데 JDBC 드라이버에 비용이 많이 들어
        성능 저하가 발생할 수 있음 (Oracle 12c, PostgreSQL에 보고 됨)

        이럴 경우 spring.jdbc.getParameterType.ignore 속성을 true로 주거나 spring.properties 클래스 경로의 루트 파일로 설정
        하는 것들을 고려해야함
    */
    public void batchInsert(final String query, final List<? extends FeedNiceBatchRequestDto> batchMessage) {
        namedParameterJdbcTemplate.batchUpdate(query, SqlParameterSourceUtils.createBatch(batchMessage.toArray()));
    }

}