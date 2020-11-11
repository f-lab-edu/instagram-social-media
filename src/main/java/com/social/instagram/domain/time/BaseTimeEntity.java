package com.social.instagram.domain.time;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
    @MappedSuperclass
    BaseTimeEntity class 아래에 정의된 변수를 컬럼으로 인식

    @EntityListeners
    BaseTimeEntity class Auditing 기능을 포함하여 자동으로 시간에 대한 값을 넣어주는 기능
*/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

}