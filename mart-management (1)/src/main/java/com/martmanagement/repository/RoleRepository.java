package com.martmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martmanagement.entity.Role;
import com.martmanagement.enumconstants.UserRoleTypeEnum;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleTypeEnum(UserRoleTypeEnum roleTypeEnum);

}
