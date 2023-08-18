package com.bonobono.backend.global.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 현재 클래스 상속시 현재 클래스의 필드들을 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // 현재 클래스 Auditing 기능 포함
public class BaseTimeEntity {
    
    @CreatedDate // Entity 생성시 생성시간 자동 저장
    private LocalDateTime createdDate;
    
    @LastModifiedDate // Entity 값 변경시 시간 자동 저장
    private LocalDateTime modifiedDate;
}
