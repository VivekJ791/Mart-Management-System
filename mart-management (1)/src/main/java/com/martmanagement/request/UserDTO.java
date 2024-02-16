package com.martmanagement.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String userName;
	private String email;
	private String mobileNo;
	private String password;
	private List<AddressDTO> addresses;
}
