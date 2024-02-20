package com.hexaware.careercrafterfinal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.careercrafterfinal.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	public Optional<UserInfo> findByName(String name);
	
	public Optional<UserInfo> findByEmail(String email);
}
