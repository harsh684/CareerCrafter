package com.hexaware.careercrafterfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>{

}