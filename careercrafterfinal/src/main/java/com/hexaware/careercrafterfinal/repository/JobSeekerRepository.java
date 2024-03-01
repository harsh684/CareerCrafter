package com.hexaware.careercrafterfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	public Optional<JobSeeker> findByPhoneNumber(String phoneNumber);
	
	@Query(value="select concat(seeker_name,',',email) from Job_Seeker where resume_id = ?1",nativeQuery = true)
	public String getNameByResumeId(long resumeId);
}
