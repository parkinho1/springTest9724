package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.SiteUser;

import jakarta.transaction.Transactional;

@Service
public class PostService {

	// 와플곰은 급하냥이 처리해 달라는것만
	// 처리해주면된다.
	// -> 컨트롤러에서 서비스단으로 데이터 조회, 수정 삭제등을 요청.
	// 그러면 그거만 해주면됨.
	// -> 다만 데이터의 가공이나 검사 등은 여기서 수행.
	// -> 가공 검사등이 끝나면 와플곰은 카피바라에게 데이터 창고의 내용을 요청하거나
	// 혹은 데이터 창고에 내용들을 저장시킬수 있음.
	// -> 카피바라는 본인만의 특별한 아이템(JPA)을 사용하여 데이터를 관리한다.

	// 파일의 저장경로는 우리가 이미 설정을 하긴했음.
	// 다만 처음에 처리할때는 해당 경로를 제대로 읽을수 없거나 문제가 발생할수 있음.
	// 이번 실습에서는 만약을 대비해서 경로를 직접지정하겠음.
	private String fileDir = "C:\\uploads\\";

	// 와플곰이 자신의 업무에 필요한
	// 데이터 저장을 위해 카피바라에게 전달하는 과정.

	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	// 와플곰이 해야할일
	// 데이터 관련 일이 필요하다.
	// CRUD 관리. ( Create, Read, Update, Delete)

	// 게시글 저장.
	public Long save(PostCreateDto dto, MultipartFile file) throws IllegalStateException, IOException {
		// 사실 저장할때 리턴타입 없어도 저장가능.
		// 지금같은경우는 리턴타입을 추가해서 무언가 값을 받고있음
		// -> 값을 통해서 데이터 저장 후의 로직을 처리하기 위함.
		String originalFileName = null;
		String storedFileName = null;

		// 비어있는지(파일 업로드를 안했는지?)
		if (file != null && !file.isEmpty()) {
			// 원래 사용자가 저장한 파일명.
			originalFileName = file.getOriginalFilename();
			// 고유한 파일명 생성
			storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;
			// transferTo : 업로드된 파일의 내용을 지정된 위치로 전송하는 기능을 수행
			// 스프링에서 흔히 파일관련된 객체를 생성할때 File 클래스를 기반으로
			// 만들어서 처리하는경우가 대부분.
			file.transferTo(new File(fileDir + storedFileName));
		}

		Post post = new Post(dto.getTitle(), dto.getContent(), originalFileName, storedFileName);

		return postRepository.save(post).getId();
	}

	// 모든 게시글 조회.
//	public List<Post> findAll() {
//		return postRepository.findAll();
//	}	
	public Page<Post> findAll(String keyword, int page) {
		List<Sort.Order>sorts = new ArrayList<>();
		// 정렬도 기준을 잡는게 가능
		// 최신순으로 정렬.
		sorts.add(Sort.Order.desc("createAt")); 
		// 페이지당 10개씩 보여주세요.
		// PageRequest : 
		// -> 변수 page : 컨트롤러로 부터 넘어오는 파라미터, 10 : 보여줄 개수, Sort.by : 정렬의 기준을 설정 
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		// Specification : JPA에서 복잡한 쿼리를 처리하기 위해 만들어진 인터페이스.
		// -> 기반은 Criteria
		// -> 목적은 동적인 쿼리를 생성할때 주로 활용하는 객체.
		// PostSpecification : Post 객체에 대한 검색조건을 정의.
		Specification<Post> spec = PostSpecification.search(keyword); 
		
		return postRepository.findAll(spec, pageable);
	}
	
	// IllegalArgumentException 추가.
	public Post 상세보기구현하기(Long id) {
		// TODO Auto-generated method stub

		// Optional<Post> 상세조회결과저장객체 = this.postRepository.findById(id);
		// return 상세조회결과저장객체.get();

		// 예외처리 적용하기 방법 1 : 람다를 활용한다(orElseThrow 메서드를 활용)
//		return this.postRepository.findById(id)
//				     .orElseThrow(
//				    () -> new IllegalArgumentException("해당 게시글이 없습니다."));

		// 람다 없이 하려면?
		Optional<Post> 상세조회결과저장객체 = this.postRepository.findById(id);
		if (!상세조회결과저장객체.isPresent()) {
			throw new IllegalArgumentException("해당 게시글이 없습니다.");
		}
		return 상세조회결과저장객체.get();
	}

	// 4 , 누렁누렁, 누렁누렁
	// -> 컴퓨터(서비스단은)는 지금 어떤 게시글을 업데이트해야하는지 정보x
	// -> 어떤걸 바꿔야할지 알려줘야한다.
	@Transactional // 객체 자체만 불러와서 잘 바꿔주고 맴버변수를 해당 엔티티를 통해
	// 변경만 잘해주면 이전보다 쉽게 데이터를 수정할수 있음.
	public void update(Long id, PostCreateDto dto) {
			Optional<Post> post = postRepository.findById(id);
			Post p1 = post.get();
			p1.update(dto.getTitle(), dto.getContent());		
		
	}

	public void delete(Long id) {
		Optional<Post> post = postRepository.findById(id);
		Post p1 = post.get();
		postRepository.delete(p1);
	}

	@Transactional
	public void recommend(Post post, SiteUser user) {
		// TODO Auto-generated method stub
		if (post.getRecommendation().contains(user)) {// 만약에~ 이미 추천을 했던 유저라면
			post.getRecommendation().remove(user);
		} else {
			post.getRecommendation().add(user);
		}
		this.postRepository.save(post);
	}

}