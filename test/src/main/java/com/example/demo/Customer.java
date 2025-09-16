package com.example.demo;

import java.math.BigDecimal;

import groovy.transform.ToString;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers") // db의 테이블 명과 대소문자까지 다 틀리다면 이렇게 table 어노테이션을 통해
                           // 맞출 수 있습니다. (매핑 할 수 있음)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
	
	@Id
	@Column(name = "customerNumber") // Column에 db콜롬 이름으로 지정해줘야 정확히 따라감 귀찮아도 다 해주면 좋음
	private Integer customerNumber;
	
	@Column(name = "customerName", nullable = false, length = 50) //nullable(Not null) length(길이)
    private String customerName;

    @Column(name = "contactLastName", nullable = false, length = 50)
    private String contactLastName;

    @Column(name = "contactFirstName", nullable = false, length = 50)
    private String contactFirstName;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;
    
    @Column(name = "addressLine1", nullable = false, length = 50)
    private String addressLine1;
    
    @Column(name = "addressLine2", length = 50) // DB상 addressLine2 에는 널값이 허용되어 있으니 nullable운 지워도 됨.
    private String addressLine2;
    
    @Column(name = "salesRepEmployeeNumber")
    private Integer salesRepEmployeeNumber;
    
    // precision = 숫자 타입에서 전체 자릿수 지정
    // -> 최대 10자리 숫자 지정
    // scale : 소수점 이하 자리수 지정
    // -> 소수점 이하 2자리까지 저장 가능
    // BigDecimal : 자바에서 고정도니 소수점 숫자를 표현하기 위한 참조타입
    // -> 주로 정확한 소수점 계산이나 금액을 계산하는 경우에많이 사용.
    // -> 크래딧한도 라던지 정확한 숫자를 계산을 요구하는 금융 데이터를 처리하기 위해
    // 	  사용될 가능성이 매우 높음.
    @Column(name = "creditLimit", precision = 10, scale = 2)
    private BigDecimal creditLimit;    
    
}
