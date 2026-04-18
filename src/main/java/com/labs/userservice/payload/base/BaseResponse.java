package com.labs.userservice.payload.base;

import lombok.Data;

@Data
public class BaseResponse {
	private int status;
	private String message;
}
