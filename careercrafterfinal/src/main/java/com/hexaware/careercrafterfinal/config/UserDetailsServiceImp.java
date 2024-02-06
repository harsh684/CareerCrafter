package com.hexaware.careercrafterfinal.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hexaware.careercrafterfinal.entities.UserInfo;
import com.hexaware.careercrafterfinal.repository.UserInfoRepository;

public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserDetailsImp::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

}
