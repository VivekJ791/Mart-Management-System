package com.martmanagement.security.response;
import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomResponseBody implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";

	@Builder.Default
	String status = SUCCESS;
	Integer statusCode;
	String message;

	Object data;

}

