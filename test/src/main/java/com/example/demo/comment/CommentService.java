package com.example.demo.comment;

import com.example.demo.Post;
import com.example.demo.user.SiteUser;

public interface CommentService {

	void create(Post post, String content, SiteUser author);
	Comment getComment(Long id);
	
}