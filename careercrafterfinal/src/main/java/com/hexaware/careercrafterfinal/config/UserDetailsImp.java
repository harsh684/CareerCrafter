package com.hexaware.careercrafterfinal.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hexaware.careercrafterfinal.entities.UserInfo;

import io.jsonwebtoken.lang.Collections;

public class UserDetailsImp implements UserDetails {

	private String name;
    private String password;
    private GrantedAuthority authority;
    //private long authorityId;
	

	public UserDetailsImp(UserInfo userInfo) {
    	name=userInfo.getName();
    	password=userInfo.getPassword();
    	authority=new SimpleGrantedAuthority(userInfo.getRole().replaceAll("\\s", ""));
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(authority);
		return authorityList;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
//	public long getAuthorityId() {
//		return authorityId;
//	}
//
//	public void setAuthorityId(long authorityId) {
//		this.authorityId = authorityId;
//	}

}
