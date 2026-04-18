package com.labs.userservice.payload.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
	private int code;
	private HttpStatus status;
	private String message;
	private List<T> data;
}
