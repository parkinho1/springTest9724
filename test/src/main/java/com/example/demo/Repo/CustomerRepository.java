package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value= "SELECT * FROM customers", nativeQuery = true)
	List<Customer> findAllNative(); // 전체 조회를 처리해볼 예정
	
}
