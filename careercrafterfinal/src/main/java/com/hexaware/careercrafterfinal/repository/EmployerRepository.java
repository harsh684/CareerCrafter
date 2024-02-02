package com.hexaware.careercrafterfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

}
