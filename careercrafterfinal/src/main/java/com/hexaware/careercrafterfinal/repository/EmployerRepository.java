package com.hexaware.careercrafterfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
	public Optional<Employer> findByPhno(String phno);
}
