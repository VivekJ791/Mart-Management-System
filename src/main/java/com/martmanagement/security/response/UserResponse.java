package com.martmanagement.security.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

	private Long id;
	private String userName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String roles;
	private String token;
	private Long mobileNo;

}
