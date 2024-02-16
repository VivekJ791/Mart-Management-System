package com.martmanagement.service;

import java.util.Optional;

import com.martmanagement.entity.User;
import com.martmanagement.request.UserDTO;

public interface UserService {

	UserDTO addOrUpdate(UserDTO userDTO);

	Optional<User> findByUserName(String userName);

}
