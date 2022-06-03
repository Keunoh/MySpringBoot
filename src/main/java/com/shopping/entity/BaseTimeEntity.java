package com.shopping.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// Entity에 대하여 AuditingEntityListener를 이용하여 리스닝한다.
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter @Setter
public abstract class BaseTimeEntity {
    @CreatedDate // 엔티티 생성시 시간을 자동으로 기록한다.
    @Column(updatable = false) // Entity 수정시 같이 갱신하지 않는다.
    private LocalDateTime regTime;

    @LastModifiedDate // 엔티티 수정시 시간을 자동으로 기록한다.
    private LocalDateTime updateTime;
}
