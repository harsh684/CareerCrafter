package com.hexaware.careercrafterfinal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.careercrafterfinal.filter.JwtAuthFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {

	@Autowired
	@Lazy
	JwtAuthFilter authFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
       return new UserDetailsServiceImp();
    }
	
	@Bean
    public  SecurityFilterChain   getSecurityFilterChain(HttpSecurity http) throws Exception {
    	
    	
    		return http.csrf().disable()
    			.authorizeHttpRequests()
    			.requestMatchers("/swagger-ui/**","/api/register/**")
    			.permitAll()
    			.and()
    			.authorizeHttpRequests().requestMatchers("/api/seeker/**","/api/employer/**","/api/profilepic/**","/api/resumedoc/**")
    			.authenticated().and() 
    			.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    			.and()
    			.authenticationProvider(myauthenticationProvider())
    			.addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class)
    			.build();
    	
    	
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider myauthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
    	return config.getAuthenticationManager();
    }
}
