package com.hexaware.careercrafterfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	public Optional<JobSeeker> findByPhoneNumber(String phoneNumber);
}
