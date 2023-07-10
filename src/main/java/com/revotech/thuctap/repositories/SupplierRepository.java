package com.revotech.thuctap.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revotech.thuctap.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	Optional<Supplier> findByCode(String code);
	boolean existsByCode(String code);
	void deleteByCode(String code);
}
