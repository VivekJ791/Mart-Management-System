package com.martmanagement.security.controller;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martmanagement.entity.User;
import com.martmanagement.security.request.AuthRequest;
import com.martmanagement.security.response.CustomResponseBody;
import com.martmanagement.security.response.UserResponse;
import com.martmanagement.security.service.UserInfoService;
import com.martmanagement.security.util.JwtUtil;
import com.martmanagement.service.UserService;

@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	UserService userService;

	@PostMapping("/generateToken")
	public ResponseEntity<Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			UserDetails userDetails = userInfoService.loadUserByUsername(authRequest.getUsername());
			User user = userService.findByUserName(userDetails.getUsername()); // accessing directly beacuse it is
																						// checked in previous method
			authenticate(authRequest.getUsername(), authRequest.getPassword(), user);

			String token = jwtUtil.generateToken(authRequest.getUsername());

			UserResponse userResponse = UserResponse.builder().id(user.getId()).firstName(user.getUserName()).token(token)
					.build();
			return ResponseEntity.status(HttpStatus.OK).body(CustomResponseBody.builder()
					.status(CustomResponseBody.SUCCESS).data(userResponse).statusCode(HttpStatus.OK.value()).build());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(CustomResponseBody.builder().status(CustomResponseBody.FAIL).data(e.getMessage())
							.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build());
		}

	}

	private void authenticate(String username, String password, User user) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new Exception("Username or Password is Incorrect");

	}

}
