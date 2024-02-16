package com.martmanagement.enumconstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserRoleTypeEnum {
	
	USER("User");

	@Getter
	private String displayName;
	
	public static List<UserRoleTypeEnum> getUserRoleTypeEnums() {
		List<UserRoleTypeEnum> userRoleTypeEnums = new ArrayList<>();
		for (UserRoleTypeEnum userRoleTypeEnum : values()) {
			Arrays.asList(userRoleTypeEnums.add(userRoleTypeEnum));
		}
		return userRoleTypeEnums;
	}
	
	static HashMap<String, UserRoleTypeEnum> hashMap = new HashMap<>();
	
	static {
		for (UserRoleTypeEnum userRoleTypeEnum : UserRoleTypeEnum.values()) {
			hashMap.put(userRoleTypeEnum.getDisplayName(), userRoleTypeEnum);
		}
	}
	
	public static UserRoleTypeEnum getUserRoleTypeEnumValuesByName(String name) {
		if (name != null && !name.isEmpty()) {
			return hashMap.get(name);
		}
		return null;
	}
}

