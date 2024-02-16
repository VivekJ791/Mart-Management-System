package com.martmanagement.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.martmanagement.entity.User;
import com.martmanagement.repository.UserRepository;
import com.martmanagement.security.response.UserInfoDetails;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username == null || username.equals("")) {
			throw new UsernameNotFoundException("Username is required");
		}

		User optionalUser = userRepository.findByUserName(username);
		if (optionalUser==null) {
			throw new UsernameNotFoundException("User Not Found :: " + username);
		}

		return new UserInfoDetails(optionalUser);
	}

}

