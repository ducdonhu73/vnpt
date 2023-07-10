package com.revotech.thuctap.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revotech.thuctap.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
	Optional<Branch> findByCode(String code);
	boolean existsByCode(String code);
}
