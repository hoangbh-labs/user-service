package com.labs.userservice.payload.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private  int code;
	private HttpStatus status;
	private String message;
}
