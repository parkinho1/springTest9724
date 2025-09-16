package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
 카피바라의 영역 (DB / Model단)
 JpaRespository 라는 다른 인터페이스를 상속받는 순간
 기본적인 데이터처리가 가능해진다.
 save, findAll, findById 
 */
@Repository
public interface PostRepository 
		extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
	
	
}
