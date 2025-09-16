package com.example.demo;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(length = 15)
	private String productCode;

	@Column(nullable = false, length = 70)
	private String productName;

	@Column(nullable = false, length = 50)
	private String productLine;

	@Column(nullable = false, length = 10)
	private String productScale;

	@Column(nullable = false, length = 50)
	private String productVendor;

	@Lob // TEXT 타입과 매핑
	@Column(nullable = false, columnDefinition = "TEXT")
	private String productDescription;

	@Column(nullable = false)
	private Short quantityInStock;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal buyPrice;

	@Column(name = "MSRP", nullable = false, precision = 10, scale = 2)
	private BigDecimal msrp;

	// Getter, Setter, 기본 생성자 등은 Lombok 이나 직접 생성
}