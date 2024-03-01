package com.hexaware.careercrafterfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>,PagingAndSortingRepository<Listing, Long>{

	@Modifying
	@Query("update Listing l set l.listingStatus = ?1 where l.listingId= ?2")
	public int updateListingStatus(String status,long listingId);
	
}
