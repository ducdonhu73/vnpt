package com.revotech.thuctap.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revotech.thuctap.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByCode(String code);
	User findByAccount_id(Integer account);
	boolean existsByCode(String code);
	void deleteByCode(String code);
}
