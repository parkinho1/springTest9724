package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

// 엔티티 설계 기법 중 하나인 공통 필드관리 기법.
@MappedSuperclass // 이 클래스는 테이블로 매핑되는 것이 아니라 필드만 물려주는 추상클래스임을 선언.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeentity {
	
	@CreatedDate
	@Column(updatable = false) // 엔티티가 수정되어도 변경안됨.
	private LocalDateTime createAt;
	
	@LastModifiedDate
	private LocalDateTime updateAt;
	
}
