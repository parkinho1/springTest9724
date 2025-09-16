package com.example.demo.Repo;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Product;
 
public interface ProductRepository extends JpaRepository<Product, String> {
	
	// 커스텀 쿼리
	// JPArepo : 자바 문법으로 DB에 요청 할 수 있도록 준비되어 있는 메서드들을 활용해서 처리.
	//  -> 하지만 현실적으로 쿼리자체가 간단하게 나오는 경우보다 복잡하게 나오는 경우도 상당수.
	
	// @Query 어노테이션을 통해 DB에서 활용하는 쿼리들을 쓸 수 있다.
	// 1. JPQL : 엔티티를 대상으로 하는 객체지향 쿼리 언어
	//     -> SQL과 문법이 거의 같음
	//     -> 특정 DB에 종속되지 않는다는 장점이 있음.
	// @Param : 서비스단에서 호출될때 파라미터를 받기 위해 쓰는 어노테이션.
	// p : Product 엔티티를 가리키는 alias
	
	// @Query("SELECT p FROM Product p WHERE p.buyPrice> :minPrice")
	// List<Product> findProduct1(@Param("minPrice") BigDecimal minPrice);
	
	// 2. Native Query
	@Query(value = "SELECT * FROM products WHERE buyprice > :minPrice", nativeQuery=true)
	List<Product> findProduct1(@Param("minPrice") BigDecimal minPrice);
	
	// JPQL vs Native Query
	// 공통점: 쿼리사용.
	// 차이점: 중심이 어디에 있느냐.
	// - JPQL은 객체 중심, Native Query는 db에 더 가까운 형태.
	
	// 현업에서 언제쓰느냐
	// - 일반적으로는 JPQL이 DB를 안탄다는 장점이 있음.
	// - 하지만 Native Query가 DBMS가 변경이 안된다라는 보장이 있다면
	//   JPQL보다 조회라던지 표현범위는 훨씬 넓기 때문에 특정 DB에 고유기능을 사용할때는 Native Query가 좋다.
	
	// 직접 쿼리를 작성하는 방법
	// 1. JPQL : 문자열 기반의 마법 주문서
	// 장점 : 직관적이고 간단한 쿼리를 빠르게 작성할 때 편함.
	// 단점 : 쿼리에 오타가 있거나 문법 오류가 있어도 에러메세지를 확인하는게 어려움.
	//       -> 컴파일 시점에 발견이 절대 안됨.
	//       -> 자바의 장점을 스스로 포기하는 행동.
	// 2. QueryDSL : 자바 코드로 짓는 쿼리의 시
	//       -> 쿼리를 문자열이 아닌 자바코드로 작성하는 방식
	//       -> 서버 실행할때 문제있으면 발견됨.
	//       -> 자동완성 기능도 사용 가능. 안정성 측면에서 큰 장점을 가져감.
	//       -> 기존 자바 문법과의 조합도 가능.
	// 단점 : 초기 세팅이 귀찮을 수 있음.
	
	
	
	
}
