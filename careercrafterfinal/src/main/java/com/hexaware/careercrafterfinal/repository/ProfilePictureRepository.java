package com.hexaware.careercrafterfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.careercrafterfinal.entities.ProfilePic;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePic, String> {

}
