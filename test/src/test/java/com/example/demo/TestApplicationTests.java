package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.Repo.CustomerRepository;
import com.example.demo.Repo.ProductRepository;
import com.example.demo.comment.Comment;
import com.example.demo.comment.CommentRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	private PostRepository postRepository;
//	@Autowired // 급하냥과 와플곰이 협력을 하기위해 마을 시스템에 와플곰을 불러달라 요청.
//	private CommentRepository commentRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void contextLoads() { 
//		List<Customer> lists = this.customerRepository.findAll();
//		System.out.println(lists.toString());

//		List<Customer> lists = customerRepository.findAllNative();
//		System.out.println(lists.toString());
		BigDecimal jonnakum = new BigDecimal("100.00");
		List<Product> result = productRepository.findProduct1(jonnakum);
//		System.out.println(result);
		assertThat(result).isNotNull();
		assertThat(result).isNotEmpty();
	}
}
