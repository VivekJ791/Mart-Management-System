package com.martmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.martmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("Select u from User u where u.userName=:username")
	User findByUserName(String username);

}
