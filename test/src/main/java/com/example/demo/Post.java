package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.demo.comment.Comment;
import com.example.demo.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

// Post 라고 하는 객체의 데이터의 설계도(Entity)
// 카피바라는 이 설계도(상자)를 보고 데이터 창고에 저장할 영역(테이블)을 만들어줌
// 이 어노테이션은 이 클래스가 JPA에 의해 관리되는 정보임을 나타낸다.

@Entity
public class Post {

	@Id // 카피바라는 이 맴버변수를 통해서 각각의 데이터들을 구분할것임.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 위의 어노테이션은 카피바라가 일일히 번호 붙이기 귀찮아서 들어온 순서대로 1번부터 부여할것임을 선언.
	private Long id;
	@Column(length = 200, nullable = false)
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	private LocalDateTime createAt;

	private String filename;
	private String filepath;

	@ManyToMany // 다대다 구현.
	Set<SiteUser> recommendation;// 추천 : 한명의 사용자가 따봉 난사하면? -> 추천조작.

	// JPA가 적용되면 기본 생성자를 요구.
	public Post() {
	}

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.createAt = LocalDateTime.now();
	}

	public Post(String title, String content, String filename, String filepath) {
		this.title = title;
		this.content = content;
		this.createAt = LocalDateTime.now();
		this.filename = filename;
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Set<SiteUser> getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Set<SiteUser> recommendation) {
		this.recommendation = recommendation;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public void update(String title2, String content2) {
		this.title = title2;
		this.content = content2;
		this.createAt = LocalDateTime.now();
	}

	// 하나의 게시글은 여러개의 댓글을 가질수 있다.
	// mappedBy = Comment 엔티티의 post 필드(맴버변수)에 의해 관계가 관리됨을 명시해준다.
	// CascadeType.REMOVE : 이 게시글이 삭제되면 관련된 모든 댓글도 삭제됨.
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;

//	public Post toEntity() {
//		return new Post(title, content);
//	}

}