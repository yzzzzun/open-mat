package com.yzzzzun.openmat.common;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime updatedDate;

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
}
