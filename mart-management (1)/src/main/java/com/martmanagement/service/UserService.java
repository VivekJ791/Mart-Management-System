package com.martmanagement.service;

import com.martmanagement.entity.User;
import com.martmanagement.request.UserDTO;

public interface UserService {

	UserDTO addOrUpdate(UserDTO userDTO);

	User findByUserName(String userName);

}
