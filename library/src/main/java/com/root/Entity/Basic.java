package com.root.Entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class Basic implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean deleted = false;
    
    @JsonIgnore
    @CreatedDate
    private Date createdAt;

    @JsonIgnore
    @LastModifiedDate
    private Date updatedAt;
    
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	@CreatedBy
    private User createdBy;
    
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	@LastModifiedBy
    private User updatedBy;
    
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deleted_by")
	private User deletedBy;
	
	@JsonIgnore
//	@Column(name = "deleted_at")
	private Date deletedAt;
}
