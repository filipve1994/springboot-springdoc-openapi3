package com.filip.examples.springbootspringdocopenapi3.models.auditing;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"creationDate", "lastModifiedDate"},
        allowGetters = true
)
@Getter
//@Setter
public abstract class Auditable {

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
    protected String createdBy;

    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Europe/Brussels")
    @Column(nullable = false, updatable = false)
    protected LocalDateTime creationDate;

    @LastModifiedBy
    protected String lastModifiedBy;

    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Brussels")
    @Column(nullable = false)
    protected LocalDateTime lastModifiedDate;

}