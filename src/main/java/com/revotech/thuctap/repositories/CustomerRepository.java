//package com.revotech.thuctap.repositories;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.revotech.thuctap.models.Customer;
//
//public interface CustomerRepository extends JpaRepository<Customer, Long>{
//	Optional<Customer> findByCode(String code);
//	boolean existsByCode(String code);
//	void deleteByCode(String code);
//}
