package com.revotech.thuctap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revotech.thuctap.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByCode(String code);
}
