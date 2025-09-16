package com.example.demo.user;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//User -> SiteUser : 바꾼이유 User 라는 이름으로는 이미 JPA에서 겹치는 컬럼들이 너무 많이 존재.
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// 이거 꼭 필여한가요?
	// -> 현재 세팅 기준에서는 설정할 필요가 있음.
	// -> JTA가 요구함.
	// (JTA는 모든 엔티티 클래스에 기본 생성자를 요구)
	// 1. 리플렉션을 통한 객체생성 구조이기 때문.
	// -> 리플렉션 : 프로그래밍에서 실행중인 프로그램의 구조를 분석하고 수정할 수 있는 기능을 제공하는 도구.
	// 2. 데이터베이스와의 맞추는 과정에서 생성자가 있어야지 그것이 가능.
	// -> 매핑 (엔티티와 데이터베이스의 매핑과정에 필요해서)

	public SiteUser() {

	}

	@Override
	public boolean equals(Object o) {
		// 객체 맨날 비교하기 귀찮아서 객체 비교를 정의해두는 메서드
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SiteUser user = (SiteUser) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() { // 검색속도 높이려고 썼다.
		return Objects.hash(id);
	}

	public SiteUser(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	private String password;
	private String role;
}
