package com.hexaware.careercrafterfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.careercrafterfinal.entities.Applications;

public interface ApplicationRepository extends JpaRepository<Applications, Long> {

	@Modifying
	@Query("update Applications a set a.status = ?1 where a.applicationId= ?2")
	public int updateStatus(String status,long applicationIds);
}
