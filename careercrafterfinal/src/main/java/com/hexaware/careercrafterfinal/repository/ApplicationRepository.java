package com.hexaware.careercrafterfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.Applications;

@Repository
public interface ApplicationRepository extends JpaRepository<Applications, Long> {

	@Modifying
	@Query("update Applications a set a.status = ?1 where a.applicationId= ?2")
	public int updateStatus(String status,long applicationIds);
	
	@Query(value = "Select seeker_id from Applications where application_id=?",nativeQuery = true)
	public int getSeekerId(long applicationId);
	
	@Query(value = "Select listing_id from Applications where application_id=?",nativeQuery = true)
	public long getListingId(long applicationId);
	
	@Query(value = "Select listing_id from Applications where seeker_id=?",nativeQuery = true)
	public List<Long> getListingIds(long seekerId);
	
}
