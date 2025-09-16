package com.example.demo.comment;
import java.time.LocalDateTime;
import org.apache.catalina.User;
import com.example.demo.Post;
import com.example.demo.user.SiteUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String content;	
	private LocalDateTime createAt;
	
	private LocalDateTime modifiedAt;
	
	@ManyToOne // 여러개의 댓글이 하나의 게시글을 바라본다.
	private Post post;
	
	@ManyToOne
	private SiteUser author;

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public Post getPost() {
		return post;
	}

	public SiteUser getAuthor() {
		return author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public void setAuthor(SiteUser author) {
		this.author = author;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}	
	
}
