package me.sathish.query.sathishaccountquery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class SathishRunMSBaseEntity {
    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(insertable = true)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(updatable = true)
    private String updatedBy;
}
