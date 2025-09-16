package com.example.demo;

import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class WordWareHouse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 스프링 용어정리집.
		// JPA(Java Persistence API)
		// ORM(Object Relational Mapping)
		// -> 프로그래밍 문법으로도 데이터 베이스를 다룰수 있는 방법.
		// -> ORM을 이용하면 개발자는 SQL을 직접 쓰지 않아도 DB를 활용하는것이 가능
		// (이론상으로는요)
		// -> JPA는 자바의 ORM 프레임워크를 JPA라 부름.
		// -> JPA를 실제로 구현한 클래스들중 대표적인것이 하이버네이트 라는 클래스가 있음.

		// @Autowired : 해당 필드나 생성자 또는 메서드에 대해 스프링 컨테이너가
		// 적절한 객체(Bean)를 찾아 자동으로 주입해주는 어노테이션
		// -> 안쓰면 생성자부터 일일히 직접 선언하고 연결하는게 너무 구찮음.

		// JUnit : 테스트코드를 작성하고 작성한 테스트 코드를 실행할때 사용하는 자바의 프레임워크
		// -> 스프링과는 별개의 프레임워크.

		// 엔티티 관련 용어 정리.
		// @Entity : 스프링이 해당 클래스를 엔티티 객체로 인식하도록 돕는 어노테이션
		// @Id : 기본키 설정
		// - 고유한 값인 컬럼(맴버변수)를 설정해야해서
		// - 중복이 없는 컬럼으로 설정해야함.
		// @GeneratedValue : 데이터 저장시 해당 속성에 값을 지정하지 않아도
		// 					 자동으로 1씩 증가하여 저장되는 어노테이션.
		// strategy = GenerationType.IDENTITY : 고유한 번호를 생성하는 방법을 지정하는 부분.
		// IDENTITY : db가 기본 키값을 자동으로 생성하도록 유도.
		//  -> DB에 키 생성을 완전히 위임하는 전략.
		//  -> 데이터를 INSERT 해야지만 ID값을 알 수 있음.
		// SEQUENCE : Oracle의 AutoIncreMent
		//  -> IDENTITY와는 다르게 INSERTY 전에 ID를 알 수 있긴함.
		// TABLE : 키 생성 전용 테이블을 하나두고 여기서 ID값을 가져오는 방식
		//  -> 사실 DB 락 유발하는 설정. 현업에서는 잘 사용하지 않음.
		// AUTO(기본값) : 사용하는 DB에 맞춰서 셋 중 하나 JPA가 알아서 선택.
		// PostgreSQL에서 사용.		
		//  -> 여러개의 엔티티가 하나의 시퀀스를 공유 할 수 있음.
		//  -> 성능 최적화에도 좋다. (미리 일정량의 ID를 받아둔 뒤 메모리에 두고 사용하는 형태)
		// TABLE : 키 생성 전용 테이블을 두고 ID를 가져오는 방식
		//  -> 이런거 있다만 알아두세요. (거의 안씀)
		
		// GenerationType : 전략설정.

		// @Column : 컬럼(맴버변수)에 다양한 제약조건을 설정할수 있음)
		// -> 열(컬럼)의 세부 설정을 진행
		// length : 열의 길이 설정
		// columnDefinition : 열 데이터의 유형이나 성격을 정의할때
		// - TEXT : 말그대로 문자(텍스트)들을 열의 데이터로 넣을수 있음.
		// - 잘모르겠다 싶으면 내가 알고있는 DB sql문법으로 작성해도 됨.
		// name : dbms 컬럼명(흔히 dbms에 써둔 컬럼명과 일치시킬때 사용)
		// nullable : null값 허용 여부. (기본값 : true)
		// unique
		// precision : 전체 자릿수 설정
		// scale : 소숫점 이하 자릿수
		// -> precision, scale 사용예시 : @Column(precision = 10, scale = 2)
		// -> decimal(10, 2);

		// @Enumerated
		// Enum 매핑 어노테이션
		// -> Enum을 쓰는 이유? : 정해진 값들만 들어와야하는 멤버변수(필드)를 관리하기 위해.
		// EnumType.STRING : Enum의 이름을 DB의 VARCAHR 컬럼에 그대로 저장.
		// -> Enum에 새로운 값이 중간에 추가되어도 그 추가된 값까지 자동으로 받아옴.
		// EnumType.ORDINAL : Integer인 컬럼에 맞출때 사용.
		// -> 쓰는 곳 거의 없다보면 됨.
				
		// 엔티티(테이블) 연관관계 매핑 어노테이션
		// @ManyToOne = N:1
		// @OneToMany = 1:N
		// @ManyToMany = N:M

		// 유효성검사(validation check) : 데이터를 검증하는 단계
		// @NotNull(message ="메세지") : 널값 불허
		// @NotBlank(message="메세지") : 공백 불허
		// @Size(min =2, max = 50, message ="메세지") : 최소 최대 크기 부여 가능
		// @Email() : 이메일 형식 검증

		// 숫자관련
		// @Min(value = 20, message = "20이하면 이 메세지뜸")
		// @Max(min과 사용방법 같음)
		// @Pattern(정규표현식을 활용하여 많은 조건을 지정할수 있음.)

		// 날짜 관련
//	    @CreatedDate
//	    private LocalDateTime createdAt;
//	    // 토심이: "생성 시간은 제가 자동으로 기록해드릴게요!"
//	    
//	    @LastModifiedDate  
//	    private LocalDateTime updatedAt;
//	    // 토심이: "수정 시간도 실시간으로 업데이트해드려요!"
//	    
//	    @CreatedBy
//	    private String createdBy;
//	    // 토심이: "누가 만들었는지도 기록할 수 있어요!"
//	    
//	    @LastModifiedBy
//	    private String lastModifiedBy;
//	    // 토심이: "마지막 수정자도 추적 가능합니다!"

		// 쿠키와 세션
		// 쿠키 : 클라이언트 로컬에 저장되는 키와 값이 들어있는 작은 데이터파일.
		// - 사용자 인증이 유효한 시간 자체를 명시할수 있어서.
		// - 유효 시간이 정해지면 브라우저가 종료되어도 인증이 유지된다는 특징이 있음.
		// - 클라이언트의 상태정보를 로컬에 저장했다가 참조.

		// 세션 : 쿠키랑 비슷한데 다만 저장위치가 해당 서비스를 제공하는 서버측.
		// 세션ID라는것을 부여한후 서버에 접속해서 종료할때까지
		// 인증상태를 유지하는것이 일반적.

		// 유효성검사 관련 어노테이션
		// @NotEmpty(message = "이 칸은 비워두면 안돼!") : 이 칸은 비워두면 안돼!
		// -> 대상 타입 : String, Collection, Map, Array
		// null값 이거나 내용물이 하나도 없는 경우를 불허.
		// -> 주의사항 : 단, 공백문자만 있는 경우는 통과 가능.
		// 예를들면 게시글 내용처럼 내용은 반드시 필요하되 공백은 허용해야 할 때 사용.
		// @NotBlank(message="공백은 인정 못해!") : 공백은 인정 못해!
		// -> 기본적으로 NotEmpty 포함.
		// -> String에만 가능.
		// 사용자 아이디, 닉네임, 게시글 제목(좀 애매) 같이 의미없는 공백을 허용하고싶지 않을 때.
		// @NotNull : 오직 null 객체가 들어있는지만 확인. 공백이나 빈 List는 허용.
		// -> 나이, 가격, 동의 여부처럼 0이나 false도 의미있는 값이라서 null 자체만 막고 싶을 때.
		// @Size(min = 8, max = 20, message = "최소 글자수 최대 글자수를 지정할때 사용.") : 최소 글자수 최대
		// 글자수를 지정할때 사용.
		// -> String, Collection 등을 활용 할 수 있음.
		// 문자열의 길이나 컬렉션의 크기가 지정된 최소 최대값 범위 안인지 확인.
		// @Min, Max : 숫자들의 크기를 제한할때.
		// @Min(value = 1, message = "주문 수량은 1개 이상 이어야 합니다");
		// -> int long Integer
		// @Email(사용 비추)
		// -> 이메일 체크. @ 있냐 없냐의 기준으로 체크
		// 1. @ 만 있으면 별의별 이메일 형식을 다 통과 시킬 수 있음.
		// ex) powerNureong@nureong@i.com
		// -> 그러니 이 어노테이션을 쓸땐 2차 인증을 무조건 걸어두는 것이 사용자나 개발자나 서로가 서로에게 머리 아픈 일이 없음.
		// @Pattern()
		// ->

		// bindingResult : 검증을 진행하면 검증의 결과를 담아낼 객체.
		// -> 검증 대상 바로 뒤에 위치 시키면됨.

		// @ControllerAdvice
		// - 모든 컨트롤러를 중앙집중식으로 관리하는 어노테이션.
		// (모든 컨트롤러에 적용되는것이 기본. 단 특정한 범위로 제한하는것도 가능.
		// 제한범위 : 해당 컨트롤러 파일, 해당 패키지, 상속관계
		// - 컨트롤러에서 공통적으로 발생하는 예외나 로직을 중앙에서 처리할수 있게 해주는 기능.
		// - AOP 하기전에 연습한다 생각하세요.

		// 페이징 처리를 돕는 클래스 (객체)
		// Page : 페이징 그 자체를 위한 클래스
		// Pageable : 페이징 처리를 실질적으로 진행하는 클래스
		// PageRequest : 현재 페이지와 한 페이지에 보여줄 게시물 개수등을 설정하여 페이징 요청을 진행하는 클래스.

		// 페이징관련 템플릿단(타임리프기준)의 주요 코드
		// th:classappend = "${!paging.hasPrevious} ? 'disabled'"
		// -> 이전 페이지가 없으면 '이전' 링크를 비활성화.
		// (해당하는 객체가 없으면 해당 링크 비활성화)
		// th:classappend = "${!paging.hasNext} ? 'disabled'"
		// -> 다음 페이지가 없으면 '다음' 링크를 비활성화.

		// th:href="@{|?page=${paging.number-1}|}" : 이전페이지 링크.
		// th:href="@{|?page=${paging.number+1}|}" : 다음페이지 링크.

		// 0부터 전체 페이지수 만큼 이 요소를 반복하여 생성.
		// 현재 순번은 page 객체(변수)에 대입.
		// th:each="page: ${#numbers.sequence(0, paging.totalPages-1)}"

		// th:classappend="${page == paging.number} ? 'active'"
		// 반복구간내에서 해당 페이지가 현재페이지와 같은경우 active 클래스를 적용한다.

		// 프로젝트 생성 후 만들어진 디렉토리 구조 설명
		// src/main/java : 자바 파일 저장공간
		// -> 컨트롤러, 서비스, DTO, 엔티티, Repo 등 서버단에서 동작하는 코드들을 저장
		// (프로젝트명 Application 파일을 통해 IoC가 발생하면서 프로젝트가 구동)
		// src/main/resource : 템플릿 저장공간.
		// -> static : 정적파일 저장공간
		//    ex) css, js, 이미지등 미디어파일
		// -> application.properties : 프로젝트 환경설정 파일
		//               .yml
		//    (스프링부트는 그래도 사용자 편의가 제법 괜찮은 프레임워크)
		// src/test/java : junit으로 스프링 부트의 테스트 도구를 사용하여
		//                 서버 실행없이 java 디렉토리에 작성한 코드를 테스트 할 수 있음.
		
		// @RestController : 스프링에서 RESTFUL 웹 서비스를 구축할때 사용.
		//  - @Controller는 리턴시 템플릿을 쫓아간다고 치면
		//  - @RestController는 리턴시 값 자체를 뱉어내는 개념이라 생각.
				
		// RestController 특징
		// 객체를 리턴할때 자동으로 JSON 형식으로 변환하여 클라이언트에 전송.
		// @Controller + @ResponseBody
		
		// 왜쓰냐? : 간결하게 RESTful api 구현하려고.
		// -> GET, POST 말고도 
		
		// ResponseEntity
		// HTTP Response(응답)을 표현하는 클래스.
		// -> 상태코드, 헤더 및 본문을 모두 포함 할 수 있음.
		
		// 200, 404, 500 등의 상태코드 지정 가능.
		// HTTP 응답을 세밀하게 제어하는 도구.
		// -> 개발자는 클라이언트에 대한 응답을 보다 명확하게 구성 할 수 있다.
		
		// web server와 was
		// Web Server : HTTP 프로토콜을 통해 클라이언트의 요청을 받아들이고
		//              웹 페이지를 반환하는 프로그램.
		//              정적인 컨텐츠들을 즉시 응답 가능한 형태로 제공.
		
		// WAS(Web Application Server)
		//  - 웹 어플리케이션(웹서비스)와 서버 환경을 만들어서 동작시키는 기능을
		//    제공하는 소프트웨어 프레임워크.
		//  - 클라이언트와 데이터베이스간의 연결을 관리하고 동적인 컨텐츠 생성과 비즈니스 로직 처리를 담당.
		
		// hibernate ddl-auto 옵션
		
		// 1. create : 기존 엔티티(테이블)들을 모두 삭제하고 새로 만들때 사용.
		//     - 운영 서버에서는 절대로 금지.
		// 2. create-drop : create와 똑같음
		//     - 서버가 종료되면 테이블을 모두 삭제.
		// 3. update : 기존 테이블 구조와 @Entity를 비교해서 바뀐 부분만 반영
		//     - 개발시에는 매우 편리 근데 엔티티 작성하다가 틀리면 불필요한 컬럼이 생길 수 있음.
		// 4. validate : 현상 유지 감시
		//     - 테이블과 엔티티가 일치하는지만 검사 다르면 에러 뱉어냄.
		// 5. none
		//     - JPA는 데이터베이스(스키마)에 아무런 영향도 주지 않음.
		//       (운영서버에서 권장되는 방식)
		
		// show-sql, format_sql
		// show-sql : JPA가 생성하는 SQL을 콘솔에 출력할지 체크하는 여부.
		// format_sql : 출력되는 SQL에 줄바꿈과 들여쓰기를 적용시켜서 가독성을 높임.
		
		// 조회
		// Post post = postRepository.findById(1);
		
		// JPA vs Hibernate
		// -> JPA를 설꼐도라 보고 Hibernate를 건축가라 보면됨
		
		// JPA : ORM에 대한 표준 명세서
		// -> @Entity등의 어노테이션이나 인터페이스 규칙을 정의
		// -> 자바 전용.
		
		// 하이버네이트
		// -> JPA라는 설계도를 보고 실제로 건물을 만드는 여러 구현체 중 하나
		// -> JPA의 기능을 사용하는 것도 물론이고 하이버네이트 고유 기능도 있음.
		// -> 가장 널리 사용되는 JPA 구현체(EclipesLink)
		
		// 결론: JPA라는 표준 설계도에 맞춰서 코드를 작성하고 실질적으로 하이버네이트라는 건축가(프로그램)가 그 코드를 해석해 DB와 소통하는 것.
		//      -> 흔히 이것을 스프링 부트에서 JPA를 쓴다라고 말할때의 정확한 의미.
		
		// show-sql, format_sql
		// show-sql : JPA가 생성하는 SQL을 콘솔에 출력할지 체크하는 여부
		// format_sql : 출력되는 SQL에 줄 바꿈과 들여쓰기를 적용시켜서 가독성을 높힘
		
		// db 테이블과 컬럼 패싱시 주의사항
		// "unable to locate column customer_number"
		//  -> 하이버네이트의 물리적 네이밍 전략.
		//  -> 하이버네이트는 기본적으로 데이터베이스는 스네이크케이스로 컬럼을 작성 했을거라 생각.
		//  -> 하이버네이트는 그래서 자바에서 카멜케이스로 써두면 DB는 거기 맞춰 스네이크 케이스 일거라 생각하는 물리적 네이밍 전략을 도입.
		// 에러의 원인: 즉 하이버네이트가 지 맘대로 스네이크라 생각한거.
		//  -> 하이버네이트한테 코드에 있는 그대로 찾으라 명령.
		
		// 테이블간 관계설정
		// 엔티티 세계의 관계 설정과 DB세계의 관계 표현방식이 근본적으로 달라 가장 헷갈리는 부분임.
		
		// 급하냥, 와플곰, 카피바라(JPA 명세 기술을 사용해서 데이터를 요청받은대로 정확하고 빠르게 찾아줌)
		
	}
}