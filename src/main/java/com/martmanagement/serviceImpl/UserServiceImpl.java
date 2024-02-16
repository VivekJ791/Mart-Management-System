package com.martmanagement.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.martmanagement.entity.Address;
import com.martmanagement.entity.Role;
import com.martmanagement.entity.User;
import com.martmanagement.entity.UserRole;
import com.martmanagement.enumconstants.UserRoleTypeEnum;
import com.martmanagement.repository.RoleRepository;
import com.martmanagement.repository.UserRepository;
import com.martmanagement.request.AddressDTO;
import com.martmanagement.request.UserDTO;
import com.martmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDTO addOrUpdate(UserDTO userDTO) {

		User user;
		final Long userId = userDTO.getId();
		if (userId != null && userId != 0) {
			user = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not Found for Id :: " + userId));
		} else {
			user = new User();
		}

		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		user.setMobileNo(userDTO.getMobileNo());
		user.setPassword(userDTO.getPassword());
		
		Role userRole = roleRepository.findByRoleTypeEnum(UserRoleTypeEnum.USER)
	            .orElseThrow(() -> new RuntimeException("USER role not found"));

	    boolean hasUserRole = user.getUserRoleList().stream()
	            .anyMatch(userRoleEntity -> userRoleEntity.getRole().equals(userRole));
	    if (!hasUserRole) {
	        UserRole userRoleEntity = new UserRole();
	        userRoleEntity.setUser(user);
	        userRoleEntity.setRole(userRole);
	        user.getUserRoleList().add(userRoleEntity);
	    }

		List<Address> addresses = new ArrayList<>();
		if (!CollectionUtils.isEmpty(userDTO.getAddresses())) {
			for (AddressDTO addressDTO : userDTO.getAddresses()) {
				Address address = new Address();
				address.setStreet(addressDTO.getStreet());
				address.setCity(addressDTO.getCity());
				address.setState(addressDTO.getState());
				address.setZipCode(addressDTO.getZipCode());
				address.setUser(user); // Link address to user
				addresses.add(address);
			}
		}
		user.setAddresses(new HashSet<>(addresses));

		user = userRepository.save(user);
		return convertUserToUserDTO(user);
	}

	private static UserDTO convertUserToUserDTO(final User user) {
		if (user == null) {
			return null;
		}
		final UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmail(user.getEmail());
		userDTO.setMobileNo(user.getMobileNo());
//		userDTO.setPassword(user.getPassword()); 

		final List<AddressDTO> addressDTOList = user.getAddresses().stream()
				.map(UserServiceImpl::convertAddressToAddressDTO).toList();
		userDTO.setAddresses(addressDTOList);

		return userDTO;
	}

	private static AddressDTO convertAddressToAddressDTO(final Address address) {
		if (address == null) {
			return null;
		}
		final AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setStreet(address.getStreet());
		addressDTO.setCity(address.getCity());
		addressDTO.setState(address.getState());
		addressDTO.setZipCode(address.getZipCode());
		return addressDTO;
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
