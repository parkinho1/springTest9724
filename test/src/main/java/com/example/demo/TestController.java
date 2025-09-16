package com.example.demo;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.user.SiteUser;
import com.example.demo.user.UserRepository;

import jakarta.validation.Valid;

@Controller // @<---- Annotation / 주석에 기능달아둔거.
public class TestController {

	@Autowired // 급하냥과 와플곰이 협력을 하기위해 마을 시스템에 와플곰을 불러달라 요청.
	private PostService postService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/test1")
	public String hi() {
		return "test";
	}

	// board.html
	@GetMapping("/show")
	public String showPostForm(Model model,
								@RequestParam(value = "page", defaultValue = "0") int page,
								@RequestParam(value = "kw", defaultValue = "") String kw) {
		// 급하냥이 데이터 어딨는지 찾기가 힘들어 와플곰에게
		// 필요한만큼의 데이터를 불러와달라 요청.
		// -> Post 타입의 List posts라는 인스턴스를 선언하고
		// 내용은 postService라는 객체로부터 findAll 이라는 메서드를 호출.
		Page<Post> posts = postService.findAll(kw, page); // 뭐하는건지 모르겠는데 중요한건 데이터가 넘어옴
		model.addAttribute("paging", posts); // model을 통해서 ui로 데이터를 넘김.
		model.addAttribute("kw", kw);
		// -> model.addAttribue(ui에서사용할 객체명, 넘길 인스턴스);
		return "board";
	}

	// GetMapping : 페이지 접근 및 조회시 사용
	// PostMapping : 데이터 저장, 수정, 삭제시 사용.
	// 게시글 작성페이지로 이동하기 위한 메서드
	@PreAuthorize("isAuthenticated()") // 이 어노테이션과 메서드 호출이 진행되면 로그인한 사용자만
	// 접근이 가능해짐.
	@GetMapping("/create")
	public String createPost(Model model) {
		model.addAttribute("dto", new PostCreateDto());
		return "create";
	}

	// 실질적인 저장을 진행할 메서드
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String createPost(@Valid @ModelAttribute("dto") PostCreateDto dto, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file) {
		
		// 간혹 dto, 엔티티 객체가 제대로 템플릿 전달이 안되는 문제가 발생 할 수 있음
		// (bindingResult 관련)
		// -> 그럴때는 해당 dto나 엔티티 객체에 위에 적힌 어노테이션을 사용 할 것.
		if (bindingResult.hasErrors()) {
			return "create";
		}

		try {
			postService.save(dto, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/show";
	}

	@GetMapping(value = "/show/{id}")
	public String showDetail(Model model, @PathVariable("id") Long id) {
		// @PathVariable 어노테이션이 있어야 템플릿(ui)에서 보낸 값을 받아올수 있음.

		// 서비스단(와플곰)한테 요청할게요.
		Post 상세보기리턴결과 = postService.상세보기구현하기(id);
		System.out.println(상세보기리턴결과.getFilepath());
		model.addAttribute("post", 상세보기리턴결과);
		return "detail";
	}

	@GetMapping(value = "/show/update/{id}")
	public String updatePost(Model model, @PathVariable("id") Long id) {
		Post post = postService.상세보기구현하기(id);
		model.addAttribute("post", post);
		return "update";
	}

	@PostMapping(value = "/show/update/{id}")
	public String updatePost(@PathVariable("id") Long id, PostCreateDto dto) {

		postService.update(id, dto);
		return "redirect:/show";

	}

	@PostMapping(value = "/show/delete/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		postService.delete(id);
		return "redirect:/show";

	}

	@GetMapping("/posts/recommend/{id}")
	public String postRecommend(@PathVariable("id") Long id, Principal principal) {
		Post post = this.postService.상세보기구현하기(id);
		Optional<SiteUser> user = this.userRepository.findByUsername(principal.getName());

		this.postService.recommend(post, user.get());

		return String.format("redirect:/show/%s", id);
	}

}