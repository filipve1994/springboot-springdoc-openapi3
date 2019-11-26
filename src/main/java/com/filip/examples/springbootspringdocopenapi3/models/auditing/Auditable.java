package com.filip.examples.springbootspringdocopenapi3.models.auditing;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

//    @CreatedBy
//    protected U createdBy;
//
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Date creationDate;
//
//    @LastModifiedBy
//    protected U lastModifiedBy;
//
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    protected Date lastModifiedDate;

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Europe/Brussels")
    protected LocalDateTime creationDate;

    @LastModifiedBy
    protected U lastModifiedBy;

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Brussels")
    protected LocalDateTime lastModifiedDate;

}