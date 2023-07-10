package com.revotech.thuctap.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revotech.thuctap.models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Optional<Account> findByCode(String code);
	boolean existsByUsernameAndPassword(String username, String password);
	Account findByUsernameAndPassword(String username, String password);
	boolean existsByCode(String code);
}
